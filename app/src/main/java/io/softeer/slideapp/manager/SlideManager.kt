package io.softeer.slideapp.manager

import io.softeer.slideapp.model.Slide

class SlideManager {

    fun changeSlideStatus(currentSlide : Slide, status : Boolean) {
        currentSlide.isSelect = status
    }

    fun changeSlideColor(currentSlide: Slide) {
        currentSlide.color.changeRandomColor()
    }

    fun increaseSlideAlpha(currentSlide : Slide) {
        currentSlide.color.increaseAlpha()
    }

    fun decreaseSlideAlpha(currentSlide : Slide) {
        currentSlide.color.decreaseAlpha()
    }

}