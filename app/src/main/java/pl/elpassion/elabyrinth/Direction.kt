package pl.elpassion.elabyrinth

import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.databind.node.ObjectNode

enum class Direction(val direction: String) {
    UP("up"), DOWN("down"), LEFT("left"), RIGHT("right");

    fun toJsonNode() = ObjectNode(JsonNodeFactory.instance).put("direction", direction)
}