package pl.elpassion.elmascarar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import org.phoenixframework.channels.Socket

class MainActivity : AppCompatActivity() {

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

    }
}
