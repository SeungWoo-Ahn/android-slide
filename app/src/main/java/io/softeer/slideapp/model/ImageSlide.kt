package io.softeer.slideapp.model

import io.softeer.slideapp.data.RGBColor
import io.softeer.slideapp.data.Size
import io.softeer.slideapp.enums.SlideType

data class ImageSlide(
    override val id: String,
    override var size: Size,
    override var color: RGBColor,
    var imgSrc: String?
) : Slide {
    override var type = SlideType.Image
    override var isSelect = false
}