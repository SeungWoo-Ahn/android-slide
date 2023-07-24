package io.softeer.slideapp.data.factory

import io.softeer.slideapp.data.enums.SlideType.*
import io.softeer.slideapp.data.model.ImageSlide
import io.softeer.slideapp.data.model.SquareSlide
import io.softeer.slideapp.data.model.Slide

class TypeSlideFactory : SlideFactory() {

    override fun createRandomSlide(): Slide {
        return when(createSlideType()) {
            Square -> {
                SquareSlide(createSlideUuid(), createSlideSize(), createSlideColor(), createSlideAlpha())
            }

            Image -> {
                ImageSlide(createSlideUuid(), null, createSlideAlpha())
            }
        }
    }
}