package io.softeer.slideapp.model

import io.softeer.slideapp.data.ImageSource
import io.softeer.slideapp.enums.SlideType

data class ImageSlide(
    override val id: String,
    var imageSource: ImageSource?,
    override var alpha: Int
) : Slide {
    override var type = SlideType.Image
    override var isSelect = false
}