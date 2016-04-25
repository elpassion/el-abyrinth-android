package pl.elpassion.elabyrinth.bot

import pl.elpassion.elabyrinth.core.Cell
import pl.elpassion.elabyrinth.core.Direction

class Board {

    val map: Set<Field>
    val visited: Set<Field>

    constructor(map: List<List<Cell>>) {
        this.map = emptySet()
        this.visited = emptySet()
    }

    fun solve(): List<Direction> {
        return listOf(Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN, Direction.DOWN)

    }
}