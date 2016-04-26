package pl.elpassion.elabyrinth

import android.util.Log
import android.view.MotionEvent
import android.view.View
import pl.elpassion.elabyrinth.core.Direction

class SwipeAndClickControl(val move: (Direction) -> Unit, val getPlayerPosition: () -> Pair<Float, Float>) : View.OnTouchListener {

    var startX: Float = 0f
    var startY: Float = 0f

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            saveStartPosition(event)
        }
        if (event.action == MotionEvent.ACTION_UP) {
            emitDirection(event)
        }
        return true
    }

    private fun saveStartPosition(event: MotionEvent) {
        startX = event.x;
        startY = event.y;
    }

    private fun emitDirection(event: MotionEvent) {
        val dx = event.x - startX;
        val dy = event.y - startY;

        Log.e("max dx dy", "" + Math.max(dx, dy))
        if (Math.max(Math.abs(dx), Math.abs(dy)) < 10) {
            emitClick(startX, startY)
        } else {
            emitSwipe(dx, dy)
        }
    }

    private fun emitClick(dx: Float, dy: Float) {
        val (playerX, playerY) = getPlayerPosition()
        if (Math.abs(dx - playerX) > Math.abs(dy - playerY)) {
            if (dx > playerX) move(Direction.RIGHT)
            else move(Direction.LEFT)
        } else {
            if (dy > playerY) move(Direction.DOWN)
            else move(Direction.UP)
        }
    }

    private fun emitSwipe(dx: Float, dy: Float) {
        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) move(Direction.SWIPE_RIGHT)
            else move(Direction.SWIPE_LEFT)
        } else {
            if (dy > 0) move(Direction.SWIPE_DOWN)
            else move(Direction.SWIPE_UP)
        }
    }
}