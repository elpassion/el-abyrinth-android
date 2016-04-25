package pl.elpassion.elabyrinth.bot

data class Field(val x: Int, val y: Int) {
    fun neighbours() =
            listOf(Field(x - 1, y), Field(x + 1, y), Field(x, y - 1), Field(x, y + 1))
}