package io.softeer.slideapp.data.repository

import io.softeer.slideapp.data.model.Slide
import io.softeer.slideapp.data.model.SquareSlide

interface SlideRepository {

    suspend fun getRemoteRandomSlide(): Slide?

    fun getAllLocalSlides(): MutableList<Slide>

    fun addLocalSlide(slide: Slide)

    fun deleteLocalSlide(slide: Slide)
}