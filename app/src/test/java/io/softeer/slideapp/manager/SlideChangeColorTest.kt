package io.softeer.slideapp.manager

import io.softeer.slideapp.enum.SlideType
import io.softeer.slideapp.factory.TypeSlideFactory
import org.junit.Assert.*
import org.junit.Test

class SlideChangeColorTest{

    private val manager = SlideManager()
    private val currentSlide = TypeSlideFactory().createSlide(SlideType.Rect)

    @Test
    fun changeSlideColor_toRandomColor_notEquals() {
        val beforeR = currentSlide.color.r
        val beforeG = currentSlide.color.g
        val beforeB = currentSlide.color.b
        manager.changeSlideColor(currentSlide)
        val afterR = currentSlide.color.r
        val afterG = currentSlide.color.g
        val afterB = currentSlide.color.b
        assertFalse(beforeR==afterR && beforeG==afterG && beforeB==afterB)
    }
}