package pl.elpassion.elabyrinth.core

import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode

enum class Direction(val direction: String) {
    UP("up"), DOWN("down"), LEFT("left"), RIGHT("right"),
    SWIPE_UP("swipe_up"), SWIPE_DOWN("swipe_down"), SWIPE_LEFT("swipe_left"), SWIPE_RIGHT("swipe_right");

    fun toJsonNode() = ObjectNode(JsonNodeFactory.instance).put("direction", direction)
}