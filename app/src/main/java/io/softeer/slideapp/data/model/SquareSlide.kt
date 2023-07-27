package io.softeer.slideapp.data.model

import android.os.Parcel
import android.os.Parcelable
import io.softeer.slideapp.data.local.RGBColor
import io.softeer.slideapp.data.enums.SlideType

data class SquareSlide(
    override val id: String,
    var size: Int,
    override var color: RGBColor,
    override var alpha: Int,
) : SlideWithColor(), Parcelable {
    override val type = SlideType.Square
    override var isSelect = false

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readParcelable(RGBColor::class.java.classLoader)!!,
        parcel.readInt()
    ) {
        isSelect = parcel.readByte() != 0.toByte()
    }

    override fun toString(): String {
        return "($id), Side: $size, $color, Alpha: $alpha"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeInt(size)
        parcel.writeParcelable(color, flags)
        parcel.writeInt(alpha)
        parcel.writeByte(if (isSelect) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SquareSlide> {
        override fun createFromParcel(parcel: Parcel): SquareSlide {
            return SquareSlide(parcel)
        }

        override fun newArray(size: Int): Array<SquareSlide?> {
            return arrayOfNulls(size)
        }
    }
}