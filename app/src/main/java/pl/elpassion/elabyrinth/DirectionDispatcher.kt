package pl.elpassion.elabyrinth

import android.view.MotionEvent
import android.view.View
import pl.elpassion.elabyrinth.core.Direction
import pl.elpassion.elabyrinth.core.Direction.*

class DirectionDispatcher(val directionListener: (Direction) -> Unit) : View.OnTouchListener {

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

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0) directionListener(RIGHT)
            else directionListener(LEFT)
        } else {
            if (dy > 0) directionListener(DOWN)
            else directionListener(UP)
        }
    }
}
