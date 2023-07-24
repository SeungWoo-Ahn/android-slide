package io.softeer.slideapp.manager

import io.softeer.slideapp.enum.SlideType
import io.softeer.slideapp.factory.TypeSlideFactory
import io.softeer.slideapp.model.Slide

class SlideManager {

    private val factory = TypeSlideFactory()

    fun createSlideInstance(): Slide {
        return factory.createSlide(SlideType.Rect)
    }

    fun changeSlideStatus(currentSlide: Slide, status: Boolean): Slide {
        currentSlide.isSelect = status
        return currentSlide
    }

    fun changeSlideColor(currentSlide: Slide): Slide {
        currentSlide.color = factory.changeOnlySlideColor(currentSlide)
        return currentSlide
    }

    fun increaseSlideAlpha(currentSlide: Slide): Slide {
        currentSlide.color = factory.increaseSlideColorAlpha(currentSlide)
        return currentSlide
    }

    fun decreaseSlideAlpha(currentSlide : Slide): Slide {
        currentSlide.color = factory.decreaseSlideColorAlpha(currentSlide)
        return currentSlide
    }

}