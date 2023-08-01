package io.softeer.slideapp.data.repository.local

import io.softeer.slideapp.data.model.Slide


class LocalDataSource(private val localDB: LocalDB) {

    fun getAllSlides(): MutableList<Slide> {
        return localDB.slides.value
    }

    fun addLocalSlide(slide: Slide) {
        localDB.addSlide(slide)
    }

    fun deleteLocalSlide(slide: Slide) {
        localDB.deleteSlide(slide)
    }
}