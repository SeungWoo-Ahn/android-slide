package io.softeer.slideapp.data.model

import io.softeer.slideapp.data.local.RGBColor
import io.softeer.slideapp.data.enums.SlideType

data class SquareSlide(
    override val id: String,
    var size: Int,
    override var color: RGBColor,
    override var alpha: Int,
) : SlideWithColor() {
    override var type = SlideType.Square
    override var isSelect = false

    override fun toString(): String {
        return "($id), Side: $size, $color, Alpha: $alpha"
    }
}