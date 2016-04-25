package pl.elpassion.elabyrinth

import pl.elpassion.elabyrinth.core.Cell
import pl.elpassion.elabyrinth.core.Cell.*

val Cell.layoutRes: Int
    get() = when (this) {
        FREE -> R.layout.free
        WALL -> R.layout.wall
        START -> R.layout.start
        END -> R.layout.end
    }