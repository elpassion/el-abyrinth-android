package pl.elpassion.elabyrinth

import org.phoenixframework.channels.Socket

class LabyrinthSocket {

    val socket = Socket("ws://192.168.1.143:4000/socket/websocket")
    val channel by lazy {
        socket.connect()
        socket.chan("games:lobby", null).apply {
            this.join()
        }
    }

    fun onMap(function: (List<List<Cell>>) -> Unit) {
        channel.on("game", {
            function(it.payload.get("map").map { row -> row.map { cell -> Cell.fromInt(cell.intValue()) } })
        })
    }

    fun move(direction: Direction) {
        channel.push("move_player", direction.toJsonNode())
    }

}