package pl.elpassion.elmascarar.dataModel

import java.util.*

class Game(val players: MutableList<Player>, val cards: MutableList<Card> = ArrayList(), val courtMoney: Int = 0, val activePlayerId: Int = 0) {

}