package io.softeer.slideapp.manager

import io.softeer.slideapp.data.factory.TypeSlideFactory
import io.softeer.slideapp.data.local.Point
import io.softeer.slideapp.data.model.DrawingSlide
import io.softeer.slideapp.data.model.ImageSlide
import io.softeer.slideapp.data.model.Slide
import io.softeer.slideapp.data.model.SlideWithColor

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
        if (currentSlide is SlideWithColor) {
            currentSlide.color = factory.createSlideColor()
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

    fun saveSlidePoints(currentSlide: Slide, points: MutableList<Point>): Slide {
        return currentSlide.apply {
            if (this is DrawingSlide) {
                this.points = points.toList()
                this.isEditable = false
                this.isSelect = true
            }
        }
    }
}