package pl.elpassion.elabyrinth

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode
import de.greenrobot.event.EventBus
import org.phoenixframework.channels.Channel
import org.phoenixframework.channels.Envelope
import org.phoenixframework.channels.Socket
import pl.elpassion.elmascarar.R
import pl.elpassion.elmascarar.dataModel.Card
import pl.elpassion.elmascarar.dataModel.Player
import pl.elpassion.elmascarar.viewModels.PlayerView

class MainActivity : AppCompatActivity() {

    val labyrinth: ViewGroup by lazy { findViewById(R.id.labyrinth) as ViewGroup }
    val socket by lazy { LabyrinthSocket() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.labyrinth)

        socket.onGame(onGame)
        labyrinth.setOnClickListener { socket.move(Direction.DOWN) }
    }

    val onGame: (Envelope) -> Unit = {
        Log.e("game", it.toString())
    }
}
