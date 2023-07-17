package io.softeer.slideapp.factory

import io.softeer.slideapp.data.RGBColor
import io.softeer.slideapp.data.Size
import io.softeer.slideapp.enum.SlideType
import io.softeer.slideapp.model.Slide
import java.util.UUID

abstract class SlideFactory {

    fun createSlideUuid() : String {
        val uniqueId = UUID.randomUUID().toString().replace("-","")
        val formattedId = mutableListOf<String>()
        formattedId.add(uniqueId.substring(0,3))
        formattedId.add(uniqueId.substring(3,6))
        formattedId.add(uniqueId.substring(6,9))
        return formattedId.joinToString("-")
    }

    abstract fun createSlide(type : SlideType, size : Size, color : RGBColor) : Slide
}