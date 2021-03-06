package pl.elpassion.elabyrinth.core

enum class Cell(val value: Int) {
    FREE(0), WALL(1), START(2), END(3);

    companion object {
        fun fromInt(value: Int): Cell {
            return values().first {
                it.value == value
            }
        }
    }
}