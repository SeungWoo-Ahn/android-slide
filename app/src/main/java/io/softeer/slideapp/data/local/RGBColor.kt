package io.softeer.slideapp.data.local

import com.google.gson.annotations.SerializedName

data class RGBColor(
    @SerializedName("R") val r: Int,
    @SerializedName("G") val g: Int,
    @SerializedName("B") val b: Int,
) {

    override fun toString(): String {
        return "R: $r, G: $g, B: $b"
    }
}
