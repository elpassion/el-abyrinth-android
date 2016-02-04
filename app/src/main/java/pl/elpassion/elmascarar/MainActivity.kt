package pl.elpassion.elmascarar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import org.phoenixframework.channels.Socket
import android.widget.LinearLayout
import pl.elpassion.elmascarar.dataModel.Card
import pl.elpassion.elmascarar.dataModel.Player

class MainActivity : AppCompatActivity() {
    val playerList by lazy { findViewById(R.id.player_list) as LinearLayout }
    val playerView by lazy { findViewById(R.id.player_view) as LinearLayout }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val socket = Socket("ws://el-mascarar.herokuapp.com/socket/websocket")
        socket.connect()
        val channel = socket.chan("games:lobby", null)

        channel.on("shout", { envelope -> Log.e("MESSAGE SHOUT: ", envelope.toString()) })

        channel.join().receive("ok", { envelope ->
            Log.e("MESSAGE OK: ", envelope.toString())
            channel.push("shout", null)
        }).receive("ignore", { envelope -> Log.e("MESSAGE IGNORE: ", envelope.toString()) })

        playerList.addView(Player(this, "Wacek", Card("Król")))
        playerList.addView(Player(this, "Janusz", Card("Królowa")))
        playerList.addView(Player(this, "Andrzej", Card("Sędzia")))
        playerView.addView(Player(this, "Mietek", Card("Wiedźma")))
    }
}
