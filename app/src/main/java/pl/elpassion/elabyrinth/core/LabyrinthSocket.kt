package pl.elpassion.elabyrinth.core

import com.fasterxml.jackson.databind.JsonNode
import org.phoenixframework.channels.Socket
import java.util.*

class LabyrinthSocket {

    val socket = Socket("ws://192.168.1.143:4000/socket/websocket")
    val channel by lazy {
        socket.connect()
        socket.chan("games:lobby", null).apply {
            this.join().receive("ok", {
                id = it.payload.get("response").get("id").asText()
            })
        }
    }
    var id = ""

    fun move(direction: Direction) {
        channel.push("move_player", direction.toJsonNode())
    }

    fun onGame(onGame: (Game) -> Unit) {
        channel.on("game", {
            onGame(Game(
                    it.payload.get("map").map { row -> row.map { cell -> Cell.Companion.fromInt(cell.intValue()) } },
                    it.payload.get("players").fields().map { playerFromNode(it, id) }.sortedBy { it.self }))
        })
    }

    private fun playerFromNode(entry: MutableMap.MutableEntry<String, JsonNode>, id: String): Player {
        return Player(entry.value.get("x").intValue(), entry.value.get("y").intValue(), entry.key == id)
    }
}

fun <T, R> Iterator<T>.map(transform: (T) -> R): List<R> {
    val list = ArrayList<R>()
    while (this.hasNext()) {
        list.add(transform(this.next()))
    }
    return list
}