package io.softeer.slideapp.data.model

import io.softeer.slideapp.data.enums.SlideType

interface Slide {
<<<<<<< HEAD
    val type: SlideType
=======
    var type: SlideType
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
    val id: String
    var alpha: Int
    var isSelect: Boolean
}