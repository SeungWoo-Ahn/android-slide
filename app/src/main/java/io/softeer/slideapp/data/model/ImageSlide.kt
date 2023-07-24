package io.softeer.slideapp.data.model

<<<<<<< HEAD
import io.softeer.slideapp.data.enums.SlideType

data class ImageSlide(
    override val id: String,
    var imageSource: ByteArray?,
    var url: String?,
    override var alpha: Int
=======
<<<<<<<< HEAD:app/src/main/java/io/softeer/slideapp/data/model/ImageSlide.kt
import io.softeer.slideapp.data.local.ImageSource
import io.softeer.slideapp.data.enums.SlideType
========
<<<<<<< HEAD
import io.softeer.slideapp.data.local.RGBColor
=======
import io.softeer.slideapp.data.ImageSource
>>>>>>> d526cc2 (Feat : Retrofit 사용 준비, Fix : dataclass 수정)
import io.softeer.slideapp.enums.SlideType
>>>>>>>> f375d22 (Fix : 폴더 구조 변경):app/src/main/java/io/softeer/slideapp/model/ImageSlide.kt

data class ImageSlide(
    override val id: String,
<<<<<<< HEAD
    override var size: Size,
    override var color: RGBColor,
=======
    var imageSource: ImageSource?,
    override var alpha: Int
>>>>>>> d526cc2 (Feat : Retrofit 사용 준비, Fix : dataclass 수정)
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
) : Slide {
    override var type = SlideType.Image
    override var isSelect = false
}