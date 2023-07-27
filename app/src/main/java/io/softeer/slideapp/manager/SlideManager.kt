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
        currentSlide.isSelect = status
        return currentSlide
    }

    fun changeSlideColor(currentSlide: Slide): Slide {
        if (currentSlide is SlideWithColor) {
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
            currentSlide.imageSource = imageByteArray
        }
        return currentSlide
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