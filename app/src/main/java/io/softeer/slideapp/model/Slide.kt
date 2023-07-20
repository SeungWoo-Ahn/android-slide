package io.softeer.slideapp.model

import io.softeer.slideapp.data.RGBColor
import io.softeer.slideapp.data.Size
import io.softeer.slideapp.enums.SlideType

interface Slide {
    val id: String
    var size: Size
    var color: RGBColor
    var type: SlideType
    var isSelect: Boolean
}