package io.softeer.slideapp.manager

import io.softeer.slideapp.data.factory.TypeSlideFactory
import io.softeer.slideapp.data.model.ImageSlide
import io.softeer.slideapp.data.model.SquareSlide
import io.softeer.slideapp.data.model.Slide

class SlideManager {

    private val factory = TypeSlideFactory()

    fun createSlideInstance(): Slide {
        return factory.createRandomSlide()
    }

    fun changeSlideStatus(currentSlide: Slide, status: Boolean): Slide {
        return currentSlide.also {
            it.isSelect = status
        }
    }

    fun changeSlideColor(currentSlide: Slide): Slide {
        return currentSlide.also {
            if (it is SquareSlide) {
                it.color = factory.createSlideColor()
            }
        }
    }

    fun increaseSlideAlpha(currentSlide: Slide): Slide {
        return currentSlide.also {
            it.alpha = factory.increaseSlideAlpha(it)
        }
    }

    fun decreaseSlideAlpha(currentSlide : Slide): Slide {
        return currentSlide.also {
            it.alpha = factory.decreaseSlideAlpha(it)
        }
    }

    fun changeSlideImageSource(currentSlide: Slide, imageByteArray: ByteArray): Slide {
        return currentSlide.also {
            if (it is ImageSlide) {
                it.imageSource = imageByteArray
            }
        }
    }

}