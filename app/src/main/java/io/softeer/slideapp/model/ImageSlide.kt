package io.softeer.slideapp.model

<<<<<<< HEAD
import io.softeer.slideapp.data.local.RGBColor
=======
import io.softeer.slideapp.data.ImageSource
>>>>>>> d526cc2 (Feat : Retrofit 사용 준비, Fix : dataclass 수정)
import io.softeer.slideapp.enums.SlideType

data class ImageSlide(
    override val id: String,
<<<<<<< HEAD
    override var size: Size,
    override var color: RGBColor,
=======
    var imageSource: ImageSource?,
    override var alpha: Int
>>>>>>> d526cc2 (Feat : Retrofit 사용 준비, Fix : dataclass 수정)
) : Slide {
    override var type = SlideType.Image
    override var isSelect = false
}