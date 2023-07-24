package io.softeer.slideapp.data

import java.util.Random

data class RGBColor(
    val r: Int,
    val g: Int,
    val b: Int,
) {
    override fun toString(): String {
        return "R: $r, G: $g, B: $b"
    }
}
