package pl.elpassion.elabyrinth.bot

import pl.elpassion.elabyrinth.core.Cell
import pl.elpassion.elabyrinth.core.Direction
import pl.elpassion.elabyrinth.core.Game
import pl.elpassion.elabyrinth.core.LabyrinthSocket

object Bot {

    val socket by lazy { LabyrinthSocket() }
    var map: List<List<Cell>>? = null

    @JvmStatic fun main(args: Array<String>) {
        socket.onGame(onGame)
    }

    val onGame: (Game) -> Unit = {
        if (map != it.map) {
            map = it.map
            solveAsync(it.map)
        }
    }

    private fun solveAsync(map: List<List<Cell>>) {
        Thread { walkThrough(Board(map).solve()) }.start()
    }


    val walkThrough: (List<Direction>) -> Unit = {
        it.forEach {
            Thread.sleep(100L)
            socket.move(it)
        }
    }
}