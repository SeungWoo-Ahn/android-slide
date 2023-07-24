package io.softeer.slideapp.data


data class RGBColor(
    val r: Int,
    val g: Int,
    val b: Int,
    val alpha: Int = 10
) {
    fun getHexColorStr(): String {
        val hexR = r.toString(16).padStart(2, '0')
        val hexG = g.toString(16).padStart(2, '0')
        val hexB = b.toString(16).padStart(2, '0')
        val hexAlpha = (alpha * 255 / 10).toString(16).padStart(2, '0')
        val hexColorStr = "$hexAlpha$hexR$hexG$hexB"
        return hexColorStr.uppercase()
    }

    override fun toString(): String {
        return "R: $r, G: $g, B: $b, Alpha: $alpha"
    }
}
