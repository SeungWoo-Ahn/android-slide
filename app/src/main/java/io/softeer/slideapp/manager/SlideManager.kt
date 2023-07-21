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
        currentSlide.let {
            it.color = factory.changeOnlySlideColor(it)
            return it
        }
    }

    fun increaseSlideAlpha(currentSlide: Slide): Slide {
        currentSlide.let {
            it.color = factory.increaseSlideColorAlpha(it)
            return it
        }
    }

    fun decreaseSlideAlpha(currentSlide : Slide): Slide {
        currentSlide.let {
            it.color = factory.decreaseSlideColorAlpha(it)
            return it
        }
    }

}