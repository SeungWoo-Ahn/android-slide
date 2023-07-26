package io.softeer.slideapp.data.model

import io.softeer.slideapp.data.enums.SlideType
import io.softeer.slideapp.data.local.Point
import io.softeer.slideapp.data.local.RGBColor

data class DrawingSlide(
    override val id: String,
    override var color: RGBColor,
    override var alpha: Int
) : SlideWithColor() {
    override val type = SlideType.Drawing
    override var isSelect = false
    var isEditable = true
    var points: List<Point> = emptyList()
}
