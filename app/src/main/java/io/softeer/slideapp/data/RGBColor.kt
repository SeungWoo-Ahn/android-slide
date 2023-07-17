package io.softeer.slideapp.data

data class RGBColor(
    var R : Int,
    var G : Int,
    var B : Int,
    var alpha : Int = 10
) {
    override fun toString(): String {
        return "R: $R, G: $G, B: $B, Alpha: $alpha"
    }
}
