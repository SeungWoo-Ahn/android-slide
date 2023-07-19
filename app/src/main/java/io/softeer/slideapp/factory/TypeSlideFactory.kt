package io.softeer.slideapp.factory

import io.softeer.slideapp.data.Size
import io.softeer.slideapp.enum.SlideType.*
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
                ImageSlide(createSlideUuid(), size, createSlideColor())
            }
        }
    }
}