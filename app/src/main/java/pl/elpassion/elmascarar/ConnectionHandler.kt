package pl.elpassion.elmascarar

import android.util.Log
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import de.greenrobot.event.EventBus
import org.phoenixframework.channels.Channel
import org.phoenixframework.channels.Envelope
import org.phoenixframework.channels.Socket

class ConnectionHandler {

    companion object {
        val bus = EventBus.getDefault()
        val serverEndPoint = "ws://el-mascarar.herokuapp.com/socket/websocket"
        val playerIdKey = "player_id"
        val gameIdKey = "game_id"
        val lobbyChannelKey = "games:lobby"
        val gameChannelKey = "join:game"
        val okKey = "ok"
    }

    fun startThreadToObtainPlayerId(playerId: Int? = null) {
        try {
            obtainPlayerId(playerId)
        } catch (e: Exception) {
            bus.post(OnServerConnectionError(e))
        }
    }

    fun startThreadToJoinGame(playerId: Int, gameId: Int) {
        try {
            joinGame(playerId, gameId)
        } catch (e: Exception) {
            bus.post(OnServerConnectionError(e))
        }
    }

    private fun obtainPlayerId(playerId: Int?) {
        val payload = ObjectNode(JsonNodeFactory.instance)
                .put(playerIdKey, playerId)
        val socket: Socket = Socket(serverEndPoint)
        socket.connect()
        val channel: Channel = socket.chan(lobbyChannelKey, payload)
        channel.join()
                .receive(okKey, onConnectedToChannel)
    }

    private val onConnectedToChannel = { envelope: Envelope ->
        val playerId = envelope.payload.get(playerIdKey).intValue()
        val gameId = envelope.payload.get(gameIdKey).intValue()
        Log.e("Connected to channel with ID: ", playerId.toString())
        bus.post(OnAssignedToGame(playerId, gameId))
    }


    private fun joinGame(playerId: Int, gameId : Int) {
        val payload = ObjectNode(JsonNodeFactory.instance)
                .put(playerIdKey, playerId)
                .put(gameIdKey, gameId)
        val socket: Socket = Socket(serverEndPoint)
        socket.connect()
        val channel: Channel = socket.chan(gameChannelKey, payload)
        channel.join()
                .receive(okKey, { envelope ->

                })
    }

}