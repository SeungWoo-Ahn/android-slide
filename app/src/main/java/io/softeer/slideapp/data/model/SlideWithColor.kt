package io.softeer.slideapp.data.model

import io.softeer.slideapp.data.local.RGBColor

abstract class SlideWithColor : Slide {

    abstract var color: RGBColor

    fun getHexColorStr(): String {
        val hexR = color.r.toString(16).padStart(2, '0')
        val hexG = color.g.toString(16).padStart(2, '0')
        val hexB = color.b.toString(16).padStart(2, '0')
        val hexAlpha = (alpha * 255 / 10).toString(16).padStart(2, '0')
        val hexColorStr = "$hexAlpha$hexR$hexG$hexB"
        return hexColorStr.uppercase()
    }
}