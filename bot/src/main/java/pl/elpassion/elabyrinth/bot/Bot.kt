package pl.elpassion.elabyrinth.bot

import pl.elpassion.elabyrinth.core.Direction
import pl.elpassion.elabyrinth.core.Game
import pl.elpassion.elabyrinth.core.LabyrinthSocket

object Bot {

    val socket by lazy { LabyrinthSocket() }
    lateinit var game: Game

    @JvmStatic fun main(args: Array<String>) {
        socket.onGame { game = it }
//        Thread.sleep(1000)
//        walkThrough(Board(game.map).solve())
    }

    val walkThrough: (List<Direction>) -> Unit = {
        it.forEach {
            Thread.sleep(1000)
            socket.move(Direction.DOWN)
        }
    }
}