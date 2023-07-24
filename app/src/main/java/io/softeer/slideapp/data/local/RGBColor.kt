package io.softeer.slideapp.data.local

<<<<<<< HEAD
import com.google.gson.annotations.SerializedName

data class RGBColor(
    @SerializedName("R") val r: Int,
    @SerializedName("G") val g: Int,
    @SerializedName("B") val b: Int,
) {

=======
import java.util.Random

data class RGBColor(
    val r: Int,
    val g: Int,
    val b: Int,
) {
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
    override fun toString(): String {
        return "R: $r, G: $g, B: $b"
    }
}
