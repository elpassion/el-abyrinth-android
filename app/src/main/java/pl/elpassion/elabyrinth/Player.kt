package pl.elpassion.elabyrinth

data class Player(val x: Int, val y: Int, val self: Boolean) {

    fun getImageResource(): Int {
        if (self) {
            return R.drawable.player
        } else {
            return R.drawable.player_other
        }
    }
}