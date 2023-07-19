package io.softeer.slideapp.viewmodel

import androidx.lifecycle.ViewModel
import io.softeer.slideapp.manager.SlideManager
import io.softeer.slideapp.model.Slide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SlideViewModel(
    private val manager: SlideManager = SlideManager()
) : ViewModel() {

    private val _currentSlide = MutableStateFlow(manager.createSlideInstance())
    val currentSlide : StateFlow<Slide> = _currentSlide
    val slideHexColor = MutableStateFlow("")
    val slideAlpha = MutableStateFlow(10)
    val slideSelect = MutableStateFlow(false)

    init {
        _currentSlide.value = manager.createSlideInstance()
    }

    private fun collectSlide(slide:  Slide) {
        slide.let {
            _currentSlide.value = it
            slideHexColor.value = it.color.getHexColorStr()
            slideAlpha.value = it.color.alpha
            slideSelect.value = it.isSelect
        }
    }

    fun changeSlideStatus(status: Boolean) {
        collectSlide(manager.changeSlideStatus(currentSlide.value, status))
    }

    fun changeSlideColor() {
        collectSlide(manager.changeSlideColor(currentSlide.value))
    }

    fun changeSlideAlpha(plus : Boolean) {
        currentSlide.value?.let {
            if (plus) {
                _currentSlide.value = manager.increaseSlideAlpha(it)
            } else {
                _currentSlide.value = manager.decreaseSlideAlpha(it)
            }
        }
    }
}