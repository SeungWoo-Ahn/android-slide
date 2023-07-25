package io.softeer.slideapp.data.model

import io.softeer.slideapp.data.enums.SlideType

data class ImageSlide(
    override val id: String,
    var imageSource: ByteArray?,
    var url: String?,
    override var alpha: Int
) : Slide {
    override var type = SlideType.Image
    override var isSelect = false
}