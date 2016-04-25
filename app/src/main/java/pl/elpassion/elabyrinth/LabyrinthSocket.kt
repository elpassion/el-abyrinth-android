package pl.elpassion.elabyrinth

import android.util.Log
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

    fun onGame(onMap: (List<List<Cell>>) -> Unit, onPlayers: (List<Player>) -> Unit) {
        channel.on("game", {
            onMap(it.payload.get("map").map { row -> row.map { cell -> Cell.fromInt(cell.intValue()) } })
            onPlayers(it.payload.get("players").fields().map { playerFromNode(it, id) })
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