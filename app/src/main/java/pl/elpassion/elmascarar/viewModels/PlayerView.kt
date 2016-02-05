package pl.elpassion.elmascarar.viewModels

import android.content.Context
import android.graphics.Color
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import pl.elpassion.elmascarar.R
import pl.elpassion.elmascarar.dataModel.Player

class PlayerView(context: Context,val player: Player) : LinearLayout(context) {
    val layout by lazy { LinearLayout.inflate(context, R.layout.player_view, this) as LinearLayout }
    val nameView by lazy { layout.findViewById(R.id.player_name) as TextView }
    val moneyView by lazy { layout.findViewById(R.id.money) as TextView }
    val cardButton by lazy { layout.findViewById(R.id.card) as Button }

    init {
        nameView.text = player.name.toString()
        moneyView.text = player.money.toString()
        if(player.card.isFaceDown)
            cardButton.setBackgroundColor(Color.CYAN)
        else
            cardButton.text = player.card.name
    }
}