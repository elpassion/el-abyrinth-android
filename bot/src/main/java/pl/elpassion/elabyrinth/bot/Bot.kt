package pl.elpassion.elabyrinth.bot

import pl.elpassion.elabyrinth.core.Direction
import pl.elpassion.elabyrinth.core.Game
import pl.elpassion.elabyrinth.core.LabyrinthSocket

object Bot {

    val socket by lazy { LabyrinthSocket() }

    @JvmStatic fun main(args: Array<String>) {
        socket.onGame(onGame)
    }

    val onGame: (Game) -> Unit = {
        socket.move(Direction.DOWN)
    }
}