package io.softeer.slideapp.manager

import io.softeer.slideapp.factory.TypeSlideFactory
import io.softeer.slideapp.model.ImageSlide
import io.softeer.slideapp.model.SquareSlide
import io.softeer.slideapp.model.Slide

class SlideManager {

    private val factory = TypeSlideFactory()

    fun createSlideInstance(): Slide {
        return factory.createRandomSlide()
    }

    fun changeSlideStatus(currentSlide: Slide, status: Boolean): Slide {
        currentSlide.isSelect = status
        return currentSlide
    }

    fun changeSlideColor(currentSlide: Slide): Slide {
        if (currentSlide is SquareSlide) {
            currentSlide.color = factory.createSlideColor()
        }
        return currentSlide
    }

    fun increaseSlideAlpha(currentSlide : Slide): Slide {
        currentSlide.alpha = factory.increaseSlideAlpha(currentSlide)
        return currentSlide
    }

    fun decreaseSlideAlpha(currentSlide : Slide): Slide {
        currentSlide.alpha = factory.decreaseSlideAlpha(currentSlide)
        return currentSlide
    }

    fun changeSlideImageSource(currentSlide: Slide, imageByteArray: ByteArray): Slide {
        if (currentSlide is ImageSlide) {
            currentSlide.imageSource = factory.changeSlideImage(imageByteArray)
        }
        return currentSlide
    }

}