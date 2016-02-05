package pl.elpassion.elmascarar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import de.greenrobot.event.EventBus
import pl.elpassion.elmascarar.dataModel.Card
import pl.elpassion.elmascarar.dataModel.Player
import pl.elpassion.elmascarar.viewModels.PlayerView

class MainActivity : AppCompatActivity() {
    val playerList by lazy { findViewById(R.id.player_list) as LinearLayout }
    val playerView by lazy { findViewById(R.id.player_view) as LinearLayout }
    val pref by lazy { PreferencesWrapper.create(this) }
    val connectionHandler: ConnectionHandler = ConnectionHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectionHandler.startThreadToObtainPlayerId(pref.getPlayerId())
        setUpPlayers()
    }

    fun onEvent(onAssignedToGame: OnAssignedToGame) {
        connectionHandler.startThreadToJoinGame(onAssignedToGame.playerId, onAssignedToGame.gameId)
    }

    private fun setUpPlayers() {
        for (player in Player.players)
            playerList.addView(PlayerView(this, player))
        playerView.addView(PlayerView(this, Player("Mietek", Card("Wiedźma"))))
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
    }
}
