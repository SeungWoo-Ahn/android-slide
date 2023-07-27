package io.softeer.slideapp.data.local

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class RGBColor(
    @SerializedName("R") val r: Int,
    @SerializedName("G") val g: Int,
    @SerializedName("B") val b: Int,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun toString(): String {
        return "R: $r, G: $g, B: $b"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(r)
        parcel.writeInt(g)
        parcel.writeInt(b)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RGBColor> {
        override fun createFromParcel(parcel: Parcel): RGBColor {
            return RGBColor(parcel)
        }

        override fun newArray(size: Int): Array<RGBColor?> {
            return arrayOfNulls(size)
        }
    }
}
