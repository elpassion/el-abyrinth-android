package pl.elpassion.elabyrinth

import android.support.annotation.LayoutRes
import pl.elpassion.elmascarar.R

enum class Cell(val value: Int, @LayoutRes val layoutRes: Int) {
    FREE(0, R.layout.free), WALL(1, R.layout.wall), START(2, R.layout.free), END(3, R.layout.end);

    companion object {
        fun fromInt(value: Int): Cell {
            return Cell.values().first {
                it.value == value
            }
        }
    }
}