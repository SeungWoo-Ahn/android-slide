package io.softeer.slideapp.factory

import io.softeer.slideapp.data.ImageSource
import io.softeer.slideapp.data.RGBColor
import io.softeer.slideapp.enums.SlideType
import io.softeer.slideapp.model.Slide
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

    fun createSlideSide(): Int {
        return random.nextInt(500) + 1
    }

    fun createSlideColor(): RGBColor {
        return RGBColor(
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(10) + 1
        )
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

    fun changeSlideImage(imageByteArray: ByteArray): ImageSource {
        return ImageSource(imageByteArray)
    }

    abstract fun createRandomSlide(): Slide
}