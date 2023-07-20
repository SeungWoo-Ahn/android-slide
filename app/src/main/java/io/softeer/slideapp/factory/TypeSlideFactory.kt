package io.softeer.slideapp.factory

import io.softeer.slideapp.enum.SlideType
import io.softeer.slideapp.enum.SlideType.*
import io.softeer.slideapp.model.RectSlide
import io.softeer.slideapp.model.Slide

class TypeSlideFactory : SlideFactory() {

    override fun createSlide(type: SlideType): Slide {
        when(type) {
            Rect -> {
                val side = createSlideSide()
                return RectSlide(createSlideUuid(), side, createSlideColor())
            }
        }
    }
}