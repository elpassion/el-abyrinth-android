package pl.elpassion.elabyrinth

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.ImageView
import pl.elpassion.elmascarar.R

class MainActivity : AppCompatActivity() {

    val labyrinth: ViewGroup by lazy { findViewById(R.id.labyrinth) as ViewGroup }
    val socket by lazy { LabyrinthSocket() }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.labyrinth)

        socket.onGame(onMap, onPlayers)
        labyrinth.setOnClickListener { socket.move(Direction.DOWN);socket.move(Direction.RIGHT) }
    }

    val onMap: (List<List<Cell>>) -> Unit = { map ->
        runOnUiThread({
            labyrinth.removeAllViews()
            map.forEach { row ->
                val rowView = labyrinth.inflate(R.layout.row) as ViewGroup
                row.forEach { cell ->
                    rowView.addView(rowView.inflate(cell.layoutRes))
                }
                labyrinth.addView(rowView)
            }
        })
    }

    val onPlayers: (List<Player>) -> Unit = { players ->
        runOnUiThread({
            players.forEach { player ->
                val rowView = labyrinth.getChildAt(player.y) as ViewGroup
                val cellView = rowView.getChildAt(player.x) as ImageView
                cellView.setImageResource(R.drawable.player)
            }
        })
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}