package pl.elpassion.elmascarar.dataModel

class Player(val name: String, val card: Card, val money: Int = 6, val id: Int = 0) {
    companion object {
        var players = listOf(Player("Wacek", Card("Król")), Player("Janusz", Card("Królowa")), Player("Andrzej", Card("Sędzia")))
    }

    fun switchCardsWith(player: Player) {
    }

    fun lookUpCard() {
    }

    fun useCard() {
    }
}