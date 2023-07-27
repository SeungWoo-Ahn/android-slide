package io.softeer.slideapp.data.repository.local

import io.softeer.slideapp.data.model.Slide


class LocalDataSource(private val localDB: LocalDB) {

    fun getAllSlides(): List<Slide> {
        return localDB.slides.value
    }

    fun saveSlides(slides: List<Slide>) {
        localDB.saveSlides(slides)
    }

    fun changeFile() {
        localDB.changeFile()
    }


}