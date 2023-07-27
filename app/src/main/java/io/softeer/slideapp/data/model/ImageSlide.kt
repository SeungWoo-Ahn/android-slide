package io.softeer.slideapp.data.model

import android.os.Parcel
import android.os.Parcelable
import io.softeer.slideapp.data.enums.SlideType

data class ImageSlide(
    override val id: String,
    var imageSource: ByteArray?,
    var url: String?,
    override var alpha: Int
) : Slide, Parcelable {
    override val type = SlideType.Image
    override var isSelect = false

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.createByteArray(),
        parcel.readString(),
        parcel.readInt()
    ) {
        isSelect = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeByteArray(imageSource)
        parcel.writeString(url)
        parcel.writeInt(alpha)
        parcel.writeByte(if (isSelect) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ImageSlide> {
        override fun createFromParcel(parcel: Parcel): ImageSlide {
            return ImageSlide(parcel)
        }

        override fun newArray(size: Int): Array<ImageSlide?> {
            return arrayOfNulls(size)
        }
    }
}