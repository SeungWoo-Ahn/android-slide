package io.softeer.slideapp.manager

import io.softeer.slideapp.factory.TypeSlideFactory
import io.softeer.slideapp.model.ImageSlide
import io.softeer.slideapp.model.Slide

class SlideManager {

    private val factory = TypeSlideFactory()

    fun createSlideInstance(): Slide {
        return factory.createSlide()
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

    fun changeSlideImageSource(currentSlide: Slide, imageByteArray: ByteArray): Slide {
        currentSlide.let {
            if (it is ImageSlide) {
                it.imageSource = factory.changeSlideImage(imageByteArray)
            }
            return currentSlide
        }
    }

}