package io.softeer.slideapp.model

import io.softeer.slideapp.data.RGBColor
import io.softeer.slideapp.data.Size
import io.softeer.slideapp.enum.SlideType

class RectSlide(
    override val id: String,
    override var size: Size,
    override var color: RGBColor,
) : Slide {

    private var side = size.width

    override var type = SlideType.Rect

    override fun toString(): String {
        return "($id), Side: $side, $color"
    }
}