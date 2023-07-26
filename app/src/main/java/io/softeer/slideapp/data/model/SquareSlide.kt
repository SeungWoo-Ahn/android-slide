package io.softeer.slideapp.data.model

<<<<<<< HEAD
import io.softeer.slideapp.data.enums.SlideType
import io.softeer.slideapp.data.local.RGBColor
=======
import io.softeer.slideapp.data.local.RGBColor
import io.softeer.slideapp.data.enums.SlideType
>>>>>>> f375d22 (Fix : 폴더 구조 변경)

data class SquareSlide(
    override val id: String,
    var size: Int,
    override var color: RGBColor,
    override var alpha: Int,
) : SlideWithColor() {
    override var type = SlideType.Square
    override var isSelect = false

    override fun toString(): String {
        return "($id), Side: $size, $color, Alpha: $alpha"
    }
}