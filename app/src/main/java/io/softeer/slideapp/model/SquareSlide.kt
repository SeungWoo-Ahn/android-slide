package io.softeer.slideapp.model

import io.softeer.slideapp.data.RGBColor
import io.softeer.slideapp.enums.SlideType

data class SquareSlide(
    override val id: String,
    var size: Int,
    var color: RGBColor,
    override var alpha: Int,
) : Slide {
    override var type = SlideType.Square
    override var isSelect = false

    fun getHexColorStr(): String {
        val hexR = color.r.toString(16).padStart(2, '0')
        val hexG = color.g.toString(16).padStart(2, '0')
        val hexB = color.b.toString(16).padStart(2, '0')
        val hexAlpha = (alpha * 255 / 10).toString(16).padStart(2, '0')
        val hexColorStr = "$hexAlpha$hexR$hexG$hexB"
        return hexColorStr.uppercase()
    }

    override fun toString(): String {
        return "($id), Side: $size, $color, Alpha: $alpha"
    }
}