package io.softeer.slideapp.data.repository.remote

import io.softeer.slideapp.api.SlideApi
import io.softeer.slideapp.api.SlideResponse

class RemoteDataSource(
    private val slideApi: SlideApi
) {
    suspend fun getRemoteAnySlides(): SlideResponse? {
        val response = slideApi.getAnySlides()
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    suspend fun getRemoteOnlySquareSlides(): SlideResponse? {
        val response = slideApi.getOnlySquareSlides()
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}