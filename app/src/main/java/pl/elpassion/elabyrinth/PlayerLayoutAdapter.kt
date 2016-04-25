package pl.elpassion.elabyrinth

import pl.elpassion.elabyrinth.core.Player

val Player.imageResource: Int
    get() = if (self) {
        R.drawable.player
    } else {
        R.drawable.player_other
    }