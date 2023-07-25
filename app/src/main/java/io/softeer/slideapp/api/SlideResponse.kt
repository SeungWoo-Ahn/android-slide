package io.softeer.slideapp.api

import com.google.gson.annotations.SerializedName
import io.softeer.slideapp.data.model.RemoteSlide

data class SlideResponse(
    @SerializedName("title") val title: String? = null,
    @SerializedName("creator") val creator: String? = null,
    @SerializedName("slides") val slides: List<RemoteSlide>? = null
)
