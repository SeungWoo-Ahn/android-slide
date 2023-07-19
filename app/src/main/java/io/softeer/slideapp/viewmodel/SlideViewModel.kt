package io.softeer.slideapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.softeer.slideapp.manager.SlideManager
import io.softeer.slideapp.model.Slide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SlideViewModel(
    private val manager: SlideManager = SlideManager()
) : ViewModel() {

    private val _currentSlide = MutableLiveData<Slide>()
    val currentSlide: LiveData<Slide> = _currentSlide

/*    private val _currentSlide = MutableStateFlow(manager.makeRectSlide())
    val currentSlide : StateFlow<Slide> = _currentSlide
    val slideHexColor = MutableStateFlow("")
    val slideAlpha = MutableStateFlow(10)
    val slideSelect = MutableStateFlow(false)*/

    init {
        /*collectSlide(currentSlide.value)*/
        _currentSlide.value = manager.makeRectSlide()
    }

/*    private fun collectSlide(slide:  Slide) {
        invokeStateFlow(_currentSlide) {
            _currentSlide.value = slide
            slideHexColor.value = it.color.getHexColorStr()
            slideAlpha.value = it.color.alpha
            slideSelect.value = it.isSelect
        }
    }*/

    fun changeSlideStatus(status: Boolean) {
        currentSlide.value?.let {
            _currentSlide.value = manager.changeSlideStatus(it, status)
        }
/*        collectSlide(manager.changeSlideStatus(currentSlide.value, status))*/
    }

    fun changeSlideColor() {
        currentSlide.value?.let {
            _currentSlide.value = manager.changeSlideColor(it)
        }
/*        collectSlide(manager.changeSlideColor(currentSlide.value))*/
    }

    fun changeSlideAlpha(plus : Boolean) {
        currentSlide.value?.let {
            if (plus) {
                _currentSlide.value = manager.increaseSlideAlpha(it)
            }
            if (!plus) {
                _currentSlide.value = manager.decreaseSlideAlpha(it)
            }
        }
/*        if (plus) {
            collectSlide(manager.increaseSlideAlpha(currentSlide.value))
        }
        if (!plus) {
            collectSlide(manager.decreaseSlideAlpha(currentSlide.value))
        }*/
    }

/*    private fun <T> invokeStateFlow(state: StateFlow<T>, collect: (T)->Unit)
    {
        viewModelScope.launch {
            state.collect{
                collect(it)
            }
        }
    }*/
}