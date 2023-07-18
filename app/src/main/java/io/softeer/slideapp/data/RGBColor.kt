package io.softeer.slideapp.data

import java.util.Random

data class RGBColor(
    var R : Int,
    var G : Int,
    var B : Int,
    var alpha : Int = 10
) {

    fun changeRandomColor() {
        val random = Random()
        R = random.nextInt(256)
        G = random.nextInt(256)
        B = random.nextInt(256)
    }

    fun increaseAlpha() {
        if (alpha == 10) return
        alpha++
    }

    fun decreaseAlpha() {
        if (alpha == 1) return
        alpha--
    }

    fun getHexColorStr() : String {
        val hexR = R.toString(16).padStart(2, '0')
        val hexG = G.toString(16).padStart(2, '0')
        val hexB = B.toString(16).padStart(2, '0')
        val hexAlpha = (alpha * 255 / 10).toString(16).padStart(2, '0')
        val hexColorStr = "$hexAlpha$hexR$hexG$hexB"
        return hexColorStr.uppercase()
    }

    override fun toString(): String {
        return "R: $R, G: $G, B: $B, Alpha: $alpha"
    }
}
