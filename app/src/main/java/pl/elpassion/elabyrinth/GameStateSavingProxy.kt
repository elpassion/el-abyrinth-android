package pl.elpassion.elabyrinth

import pl.elpassion.elabyrinth.core.Game
import pl.elpassion.elabyrinth.core.Player

class GameStateSavingProxy(val onNewMap: (Game) -> Unit, val onPlayersOnly: (List<Player>, List<Player>) -> Unit) : (Game) -> Unit {

    var previousGame: Game? = null

    override fun invoke(game: Game) {
        if (game.map == previousGame?.map) {
            onPlayersOnly(previousGame!!.players, game.players)
        } else {
            onNewMap(game)
        }
        previousGame = game
    }

}