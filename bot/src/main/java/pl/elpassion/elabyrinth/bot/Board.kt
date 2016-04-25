package pl.elpassion.elabyrinth.bot

import pl.elpassion.elabyrinth.core.Cell
import pl.elpassion.elabyrinth.core.Direction
import java.util.*

class Board {

    val map: Set<Field>
    val start = Field(1, 1)
    val visited: MutableSet<Field> = mutableSetOf(start)
    val finish: Field

    constructor(map: List<List<Cell>>) {
        this.map = map.withIndex().map { row -> val y = row.index; row.value.withIndex().filter { it.value != Cell.WALL }.map { Field(it.index, y) } }.flatten().toSet()
        this.finish = map.withIndex().map { row -> val y = row.index; row.value.withIndex().filter { it.value == Cell.END }.map { Field(it.index, y) } }.flatten().first()
    }

    fun solve(): List<Direction> {
        return solve(Stack<Field>().apply { push(Field(1, 1)) })
    }

    private fun solve(stack: Stack<Field>): List<Direction> {
        if (stack.peek() == finish) {
            return stack.toDirectionList()
        } else {
            val next = anyUnvisitedNeighbour(stack.peek())
            if (next != null) {
                visited.add(next)
                return solve(stack.apply { push(next) })
            } else {
                return solve(stack.apply { pop() })
            }
        }
    }

    private fun anyUnvisitedNeighbour(field: Field): Field? {
        val neighbours = field.neighbours()
        return map.filter { neighbours.contains(it) && !visited.contains(it) }.firstOrNull()
    }
}

fun Stack<Field>.toDirectionList(): List<Direction> {
    val list = toList()
    val moves = list.dropLast(1).zip(list.drop(1))
    return moves.map { toDirection(it.first, it.second) }
}

fun toDirection(first: Field, second: Field) =
        if (first.x == second.x) {
            if (first.y < second.y) {
                Direction.DOWN
            } else {
                Direction.UP
            }
        } else {
            if (first.x < second.x) {
                Direction.RIGHT
            } else {
                Direction.LEFT
            }
        }
