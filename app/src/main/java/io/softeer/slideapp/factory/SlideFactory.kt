package io.softeer.slideapp.factory

import io.softeer.slideapp.data.ImageSource
import io.softeer.slideapp.data.RGBColor
import io.softeer.slideapp.enums.SlideType
import io.softeer.slideapp.model.Slide
import java.util.Random
import java.util.UUID

abstract class SlideFactory {

    fun createSlideUuid(): String {
        val uniqueId = UUID.randomUUID().toString().replace("-", "")
        val formattedId = mutableListOf<String>()
        formattedId.add(uniqueId.substring(0, 3))
        formattedId.add(uniqueId.substring(3, 6))
        formattedId.add(uniqueId.substring(6, 9))
        return formattedId.joinToString("-")
    }

    fun createSlideSide(): Int {
        return Random().nextInt(500) + 1
    }

    fun createSlideColor(): RGBColor {
        val random = Random()
        return RGBColor(random.nextInt(256), random.nextInt(256), random.nextInt(256), random.nextInt(10) + 1)
    }

    fun createSlideType(): SlideType {
        val random = Random()
        val slideTypeList = SlideType.values()
        return slideTypeList[random.nextInt(slideTypeList.size)]
    }

    fun changeSlideImage(imageByteArray: ByteArray): ImageSource {
        return ImageSource(imageByteArray)
    }

    abstract fun createRandomSlide(): Slide
}