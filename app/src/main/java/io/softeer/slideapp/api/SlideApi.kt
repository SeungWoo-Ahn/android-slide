package io.softeer.slideapp.api

import retrofit2.Response
import retrofit2.http.GET

interface SlideApi {

    @GET("image-slides.json")
    suspend fun getAnySlides(): Response<SlideResponse>

    @GET("square-only-slides.json")
    suspend fun getOnlySquareSlides(): Response<SlideResponse>
}