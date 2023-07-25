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
        return currentSlide.apply {
            this.isSelect = status
        }
    }

    fun changeSlideColor(currentSlide: Slide): Slide {
        return currentSlide.apply {
            if (this is SquareSlide) {
                this.color = factory.createSlideColor()
            }
        }
    }

    fun increaseSlideAlpha(currentSlide: Slide): Slide {
        return currentSlide.apply {
            this.alpha = factory.increaseSlideAlpha(this)
        }
    }

    fun decreaseSlideAlpha(currentSlide : Slide): Slide {
        return currentSlide.apply {
            this.alpha = factory.decreaseSlideAlpha(this)
        }
    }

    fun changeSlideImageSource(currentSlide: Slide, imageByteArray: ByteArray): Slide {
        return currentSlide.apply {
            if (this is ImageSlide) {
                this.imageSource = imageByteArray
            }
        }
    }

}