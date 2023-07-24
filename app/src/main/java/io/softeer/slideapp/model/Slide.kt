package io.softeer.slideapp.model

import io.softeer.slideapp.enums.SlideType

interface Slide {
    var type: SlideType
    val id: String
    var alpha: Int
    var isSelect: Boolean
}