package io.softeer.slideapp.manager

import io.softeer.slideapp.factory.TypeSlideFactory
import io.softeer.slideapp.model.ImageSlide
import io.softeer.slideapp.model.Slide

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
            this.color = factory.changeOnlySlideColor(this)
        }
    }

    fun increaseSlideAlpha(currentSlide: Slide): Slide {
        return currentSlide.apply {
            this.color = factory.increaseSlideColorAlpha(this)
        }
    }

    fun decreaseSlideAlpha(currentSlide : Slide): Slide {
        return currentSlide.apply {
            this.color = factory.decreaseSlideColorAlpha(this)
        }
    }

    fun changeSlideImageSource(currentSlide: Slide, imageByteArray: ByteArray): Slide {
        return currentSlide.apply {
            if (this is ImageSlide) {
                this.imageSource = factory.changeSlideImage(imageByteArray)
            }
        }
    }

}