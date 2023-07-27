package io.softeer.slideapp.data.repository

import io.softeer.slideapp.data.model.Slide

interface SlideRepository {

    suspend fun getRemoteRandomSlide(): Slide?

    fun getAllLocalSlides(): List<Slide>

    fun saveSlides(slides: List<Slide>)

    fun changeFile()
}