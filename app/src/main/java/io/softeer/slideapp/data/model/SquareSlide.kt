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
    var color: RGBColor,
    override var alpha: Int,
) : Slide {
    override var type = SlideType.Square
    override var isSelect = false

    fun getHexColorStr(): String {
        val hexR = color.r.toString(16).padStart(2, '0')
        val hexG = color.g.toString(16).padStart(2, '0')
        val hexB = color.b.toString(16).padStart(2, '0')
        val hexAlpha = (alpha * 255 / 10).toString(16).padStart(2, '0')
        val hexColorStr = "$hexAlpha$hexR$hexG$hexB"
        return hexColorStr.uppercase()
    }

    override fun toString(): String {
        return "($id), Side: $size, $color, Alpha: $alpha"
    }
}