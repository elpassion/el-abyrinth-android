package pl.elpassion.elabyrinth

import android.util.Log
import org.phoenixframework.channels.Envelope
import org.phoenixframework.channels.Socket

class LabyrinthSocket {

    val socket = Socket("ws://192.168.1.143:4000/socket/websocket")
    val channel by lazy {
        socket.connect()
        socket.chan("games:lobby", null).apply {
            this.join()
        }
    }

    fun onGame(function: (Envelope) -> Unit) {
        channel.on("game", function)
    }

    fun move(direction: Direction) {
        channel.push("move_player", direction.toJsonNode())
    }

}