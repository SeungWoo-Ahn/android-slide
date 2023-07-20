package io.softeer.slideapp.factory

import io.softeer.slideapp.data.RGBColor
import io.softeer.slideapp.data.Size
import io.softeer.slideapp.enums.SlideType.*
import io.softeer.slideapp.model.ImageSlide
import io.softeer.slideapp.model.RectSlide
import io.softeer.slideapp.model.Slide

class TypeSlideFactory : SlideFactory() {

    override fun createSlide(): Slide {
        return when(createSlideType()) {
            Rect -> {
                val side = createSlideSide()
                RectSlide(createSlideUuid(), side, createSlideColor())
            }

            Image -> {
                val size = Size(createSlideSide(),createSlideSide())
                val color = RGBColor(255,255,255,10)
                ImageSlide(createSlideUuid(), size, color)
            }
        }
    }
}