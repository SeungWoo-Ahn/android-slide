package io.softeer.slideapp.data.model

import io.softeer.slideapp.data.local.ImageSource
import io.softeer.slideapp.data.enums.SlideType

data class ImageSlide(
    override val id: String,
    var imageSource: ImageSource?,
    override var alpha: Int
) : Slide {
    override var type = SlideType.Image
    override var isSelect = false
}