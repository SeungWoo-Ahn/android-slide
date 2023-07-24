package io.softeer.slideapp.data.model

import io.softeer.slideapp.data.enums.SlideType

interface Slide {
    var type: SlideType
    val id: String
    var alpha: Int
    var isSelect: Boolean
}