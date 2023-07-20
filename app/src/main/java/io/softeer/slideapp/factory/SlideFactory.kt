package io.softeer.slideapp.factory

import io.softeer.slideapp.data.RGBColor
import io.softeer.slideapp.enums.SlideType
import io.softeer.slideapp.model.Slide
import java.util.Random
import java.util.UUID

abstract class SlideFactory {

    private val random = Random()

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

    fun changeOnlySlideColor(slide: Slide): RGBColor {
        return RGBColor(
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256),
            slide.color.alpha
        )
    }

    fun increaseSlideColorAlpha(slide: Slide): RGBColor {
        var currentAlpha = slide.color.alpha
        if (currentAlpha != 10) {
            currentAlpha++
        }
        return RGBColor(
            slide.color.r,
            slide.color.g,
            slide.color.b,
            currentAlpha
        )
    }

    fun decreaseSlideColorAlpha(slide: Slide): RGBColor {
        var currentAlpha = slide.color.alpha
        if (currentAlpha != 1) {
            currentAlpha--
        }
        return RGBColor(
            slide.color.r,
            slide.color.g,
            slide.color.b,
            currentAlpha
        )
    }

    fun createSlideType(): SlideType {
        val random = Random()
        val slideTypeList = SlideType.values()
        return slideTypeList[random.nextInt(slideTypeList.size)]
    }

    abstract fun createSlide(): Slide
}