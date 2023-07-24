package io.softeer.slideapp.data.factory

<<<<<<< HEAD
import io.softeer.slideapp.data.local.RGBColor
import io.softeer.slideapp.data.enums.SlideType
import io.softeer.slideapp.data.model.Slide
=======
import io.softeer.slideapp.data.local.ImageSource
import io.softeer.slideapp.data.local.RGBColor
<<<<<<<< HEAD:app/src/main/java/io/softeer/slideapp/data/factory/SlideFactory.kt
import io.softeer.slideapp.data.enums.SlideType
import io.softeer.slideapp.data.model.Slide
========
import io.softeer.slideapp.enums.SlideType
import io.softeer.slideapp.model.Slide
>>>>>>>> f375d22 (Fix : 폴더 구조 변경):app/src/main/java/io/softeer/slideapp/factory/SlideFactory.kt
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
import java.util.Random
import java.util.UUID

abstract class SlideFactory {

    private val random = Random()

    fun createSlideType(): SlideType {
        val slideTypeList = SlideType.values()
        return slideTypeList[random.nextInt(slideTypeList.size)]
    }

    fun createSlideUuid(): String {
        val uniqueId = UUID.randomUUID().toString().replace("-", "")
        val formattedId = mutableListOf<String>()
        formattedId.add(uniqueId.substring(0, 3))
        formattedId.add(uniqueId.substring(3, 6))
        formattedId.add(uniqueId.substring(6, 9))
        return formattedId.joinToString("-")
    }

    fun createSlideSize(): Int {
        return random.nextInt(500) + 1
    }

    fun createSlideColor(): RGBColor {
<<<<<<< HEAD
        return RGBColor(
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256),
        )
=======
        val random = Random()
        return RGBColor(random.nextInt(256), random.nextInt(256), random.nextInt(256))
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
    }

    fun createSlideAlpha(): Int {
        return random.nextInt(10) + 1
    }

    fun increaseSlideAlpha(slide: Slide): Int {
        val alpha = slide.alpha
        if (alpha == 10) {
            return alpha
        }
        return alpha + 1
    }

    fun decreaseSlideAlpha(slide: Slide): Int {
        val alpha = slide.alpha
        if (alpha == 1) {
            return alpha
        }
        return alpha - 1
    }

<<<<<<< HEAD
=======
    fun changeSlideImage(imageByteArray: ByteArray): ImageSource {
        return ImageSource(imageByteArray)
    }

>>>>>>> f375d22 (Fix : 폴더 구조 변경)
    abstract fun createRandomSlide(): Slide
}