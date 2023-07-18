package io.softeer.slideapp.viewmodel

import androidx.lifecycle.ViewModel
import io.softeer.slideapp.manager.SlideManager
import io.softeer.slideapp.model.Slide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SlideViewModel(val manager : SlideManager) : ViewModel() {

    private val _currentSlide = MutableStateFlow<Slide?>(null)
    val currentSlide : StateFlow<Slide?> get() = _currentSlide

}