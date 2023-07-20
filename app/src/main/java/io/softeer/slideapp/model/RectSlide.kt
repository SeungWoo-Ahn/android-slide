package io.softeer.slideapp.model

import io.softeer.slideapp.data.RGBColor
import io.softeer.slideapp.data.Size
import io.softeer.slideapp.enums.SlideType

data class RectSlide(
    override val id: String,
    private var side: Int,
    override var color: RGBColor,
) : Slide {
    override var size = Size(side, side)
    override var type = SlideType.Rect
    override var isSelect = false

    override fun toString(): String {
        return "($id), Side: $side, $color"
    }
}