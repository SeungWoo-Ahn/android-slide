package io.softeer.slideapp.factory

import io.softeer.slideapp.enums.SlideType.*
import io.softeer.slideapp.model.ImageSlide
import io.softeer.slideapp.model.SquareSlide
import io.softeer.slideapp.model.Slide

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