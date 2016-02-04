package pl.elpassion.elmascarar.dataModel

import android.content.Context
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import pl.elpassion.elmascarar.R

class Player(context: Context, val name: String, var card: Card, var money: Int = 6) : LinearLayout(context) {
    val layout by lazy { inflate(context, R.layout.player_view, this) as LinearLayout }
    val nameView by lazy { layout.findViewById(R.id.player_name) as TextView }
    val moneyView by lazy { layout.findViewById(R.id.money) as TextView }
    val cardButton by lazy { layout.findViewById(R.id.card) as Button }

    init {
        nameView.text = name.toString()
        moneyView.text = money.toString()
        cardButton.text = card.name
    }

    fun switchCardsWith(player: Player) {
    }

    fun lookUpCard() {
    }

    fun useCard() {
    }
}