package pl.elpassion.elabyrinth

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.ImageView
import pl.elpassion.elabyrinth.core.Game
import pl.elpassion.elabyrinth.core.LabyrinthSocket

class MainActivity : AppCompatActivity() {

    val labyrinth: ViewGroup by lazy { findViewById(R.id.labyrinth) as ViewGroup }
    val socket by lazy { LabyrinthSocket() }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.labyrinth)

        socket.onGame(onGame)
        labyrinth.setOnTouchListener(DirectionDispatcher({ socket.move(it) }))
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
            game.players.forEach { player ->
                val rowView = labyrinth.getChildAt(player.y) as ViewGroup
                val cellView = rowView.getChildAt(player.x) as ImageView
                cellView.setImageResource(player.imageResource)
            }
        })
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}