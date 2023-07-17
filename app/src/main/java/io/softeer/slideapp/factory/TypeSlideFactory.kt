package io.softeer.slideapp.factory

import io.softeer.slideapp.data.RGBColor
import io.softeer.slideapp.data.Size
import io.softeer.slideapp.enum.SlideType
import io.softeer.slideapp.enum.SlideType.*
import io.softeer.slideapp.model.RectSlide
import io.softeer.slideapp.model.Slide

class TypeSlideFactory : SlideFactory() {

    override fun createSlide(type: SlideType, size: Size, color: RGBColor): Slide {
        when(type) {
            Rect -> return RectSlide(createSlideUuid(), size, color)
        }
    }
}