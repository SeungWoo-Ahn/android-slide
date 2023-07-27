package io.softeer.slideapp.data.model

import android.os.Parcel
import android.os.Parcelable
import io.softeer.slideapp.data.enums.SlideType
import io.softeer.slideapp.data.local.Point
import io.softeer.slideapp.data.local.RGBColor

data class DrawingSlide(
    override val id: String,
    override var color: RGBColor,
    override var alpha: Int
) : SlideWithColor(), Parcelable {
    override val type = SlideType.Drawing
    override var isSelect = false
    var isEditable = true
    var points: List<Point> = emptyList()

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readParcelable(RGBColor::class.java.classLoader)!!,
        parcel.readInt()
    ) {
        isSelect = parcel.readByte() != 0.toByte()
        isEditable = parcel.readByte() != 0.toByte()
        points = parcel.createTypedArrayList(Point.CREATOR) ?: emptyList()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeParcelable(color, flags)
        parcel.writeInt(alpha)
        parcel.writeByte(if (isSelect) 1 else 0)
        parcel.writeByte(if (isEditable) 1 else 0)
        parcel.writeTypedList(points)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DrawingSlide> {
        override fun createFromParcel(parcel: Parcel): DrawingSlide {
            return DrawingSlide(parcel)
        }

        override fun newArray(size: Int): Array<DrawingSlide?> {
            return arrayOfNulls(size)
        }
    }
}
