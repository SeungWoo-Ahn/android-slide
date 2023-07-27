package io.softeer.slideapp.data.repository.local

import io.softeer.slideapp.data.model.Slide
import io.softeer.slideapp.data.model.SlideFile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LocalDB {

    private val _slideFiles: MutableMap<Int, SlideFile> = mutableMapOf()
    private val _slides = MutableStateFlow(emptyList<Slide>())
    val slides: StateFlow<List<Slide>> = _slides

    fun saveSlides(slides: List<Slide>) {
        _slides.value = slides
    }

    fun changeFile() {
        val currentSlides = mutableListOf<Slide>().apply {
            addAll(_slides.value)
        }
        val slideFile = SlideFile(currentSlides.toList())
        val fileSize = _slideFiles.values.size
        _slideFiles[fileSize] = slideFile
        _slides.value = emptyList<Slide>().toMutableList()
    }
}