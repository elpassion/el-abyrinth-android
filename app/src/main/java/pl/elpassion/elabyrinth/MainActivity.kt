package pl.elpassion.elabyrinth

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.*
import android.widget.ImageView
import pl.elpassion.elabyrinth.core.Direction
import pl.elpassion.elabyrinth.core.Game
import pl.elpassion.elabyrinth.core.LabyrinthSocket
import pl.elpassion.elabyrinth.core.Player

class MainActivity : AppCompatActivity() {

    val labyrinth: ViewGroup by lazy { findViewById(R.id.labyrinth) as ViewGroup }
    val socket by lazy { LabyrinthSocket() }
    val playerLocation = IntArray(2)

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.labyrinth)

        socket.onGame(GameStateSavingProxy(onGame, onPlayersOnly))
        findViewById(R.id.control_layer).setOnTouchListener(SwipeAndClickControl(move, getPlayerPosition))
    }

    val onGame: (Game) -> Unit = { game ->
        runOnUiThread({
            labyrinth.removeAllViews()
            game.map.forEach { row ->
                val rowView = labyrinth.inflate(R.layout.row) as ViewGroup
                row.forEach { cell ->
                    rowView.addView(rowView.inflate(cell.layoutRes))
                }
                labyrinth.addView(rowView)
            }
            game.players.showPlayers()
        })
    }

    val onPlayersOnly: (List<Player>, List<Player>) -> Unit = { old, new ->
        runOnUiThread({
            old.forEach { player ->
                val rowView = labyrinth.getChildAt(player.y) as ViewGroup
                val cellView = rowView.getChildAt(player.x) as ImageView
                cellView.setImageDrawable(null)
            }
            new.showPlayers()
        })
    }

    private fun List<Player>.showPlayers() {
        forEach { player ->
            val rowView = labyrinth.getChildAt(player.y) as ViewGroup
            val cellView = rowView.getChildAt(player.x) as ImageView
            cellView.setImageResource(player.imageResource)
            if (player.self) {
                cellView.getLocationOnScreen(playerLocation)
            }
        }
    }

    val getPlayerPosition: () -> Pair<Float, Float> = {
        playerLocation[0].toFloat() to playerLocation[1].toFloat()
    }

    val move: (Direction) -> Unit = {
        Log.e("Move", it.toString())
        Thread { socket.move(it) }.start()
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}