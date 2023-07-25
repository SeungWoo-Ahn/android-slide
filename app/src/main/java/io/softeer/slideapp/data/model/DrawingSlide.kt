package io.softeer.slideapp.data.model

import io.softeer.slideapp.data.enums.SlideType
import io.softeer.slideapp.data.local.RGBColor

data class DrawingSlide(
    override val id: String,
    var color: RGBColor,
    override var alpha: Int
) : Slide {
    override val type = SlideType.Drawing
    override var isSelect = false
}
