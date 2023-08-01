package io.softeer.slideapp.data.model

import com.google.gson.annotations.SerializedName
import io.softeer.slideapp.data.local.RGBColor

data class RemoteSlide(
    @SerializedName("type") val type: String,
    @SerializedName("id") val id: String,
    @SerializedName("alpha") val alpha: Int,
    @SerializedName("size") val size: Int?,
    @SerializedName("color") val color: RGBColor?,
    @SerializedName("url") val url: String?
) {

    fun asSquareSlide(): SquareSlide = SquareSlide(
        id = id,
        size = size!!,
        color = color!!,
        alpha = alpha
    )

    fun asImageSlide(): ImageSlide = ImageSlide(
        id = id,
        null,
        url = url,
        alpha = alpha
    )
}
