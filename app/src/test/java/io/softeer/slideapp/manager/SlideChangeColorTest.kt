package io.softeer.slideapp.manager

import io.softeer.slideapp.enums.SlideType
import io.softeer.slideapp.factory.TypeSlideFactory
import org.junit.Assert.*
import org.junit.Test

class SlideChangeColorTest{

    private val manager = SlideManager()
    private val currentSlide = TypeSlideFactory().createSlide(SlideType.Square)

    @Test
    fun changeSlideColor_toRandomColor_notEquals() {
        val beforeR = currentSlide.color.R
        val beforeG = currentSlide.color.G
        val beforeB = currentSlide.color.B
        manager.changeSlideColor(currentSlide)
        val afterR = currentSlide.color.R
        val afterG = currentSlide.color.G
        val afterB = currentSlide.color.B
        assertFalse(beforeR==afterR && beforeG==afterG && beforeB==afterB)
    }
}