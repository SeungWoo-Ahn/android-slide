package io.softeer.slideapp.manager

import io.softeer.slideapp.enum.SlideType
import io.softeer.slideapp.factory.TypeSlideFactory
import io.softeer.slideapp.model.Slide

class SlideManager {

    private val factory = TypeSlideFactory()

    fun makeRectSlide() : Slide {
        return factory.createSlide(SlideType.Rect)
    }

    fun changeSlideStatus(currentSlide : Slide, status : Boolean) : Slide {
        currentSlide.isSelect = status
        return currentSlide
    }

    fun changeSlideColor(currentSlide: Slide) : Slide {
        currentSlide.color.changeRandomColor()
        return currentSlide
    }

    fun increaseSlideAlpha(currentSlide : Slide) : Slide {
        currentSlide.color.increaseAlpha()
        return currentSlide
    }

    fun decreaseSlideAlpha(currentSlide : Slide) : Slide {
        currentSlide.color.decreaseAlpha()
        return currentSlide
    }

}