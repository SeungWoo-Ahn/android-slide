package io.softeer.slideapp.data.repository.local

import io.softeer.slideapp.data.model.Slide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LocalDB {
    private val _slides = MutableStateFlow(emptyList<Slide>().toMutableList())
    val slides: StateFlow<MutableList<Slide>> = _slides

    fun addSlide(slide: Slide) {
        val originSlides = slides.value
        originSlides.add(slide)
        _slides.value = originSlides
    }

    fun deleteSlide(slide: Slide) {
        val originSlides = slides.value
        originSlides.remove(slide)
        _slides.value = originSlides
    }
}