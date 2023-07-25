package io.softeer.slideapp.data.repository

import io.softeer.slideapp.data.enums.SlideType
import io.softeer.slideapp.data.model.Slide
import io.softeer.slideapp.data.model.SquareSlide
import io.softeer.slideapp.data.repository.local.LocalDataSource
import io.softeer.slideapp.data.repository.remote.RemoteDataSource
import java.util.Random

class SlideRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : SlideRepository {

    private val random = Random()

    override suspend fun getRemoteRandomSlide(): Slide? {
        val randomApiNumber = random.nextInt(SlideType.values().size) - 1
        val response = if (randomApiNumber == 0) remoteDataSource.getRemoteOnlySquareSlides()?.slides else remoteDataSource.getRemoteAnySlides()?.slides
        if (response != null) {
            val size = response.size
            val randomIndex = random.nextInt(size)
            val randomSlide = response[randomIndex]
            return if (randomSlide.type == SlideType.Square.name) {
                randomSlide.asSquareSlide()
            } else {
                randomSlide.asImageSlide()
            }
        }
        return null
    }

    override fun getAllLocalSlides(): MutableList<Slide> {
        return localDataSource.getAllSlides()
    }

    override fun addLocalSlide(slide: Slide) {
        localDataSource.addLocalSlide(slide)
    }

    override fun deleteLocalSlide(slide: Slide) {
        localDataSource.deleteLocalSlide(slide)
    }

}