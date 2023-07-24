package io.softeer.slideapp.manager

import io.softeer.slideapp.data.enums.SlideType
import io.softeer.slideapp.data.factory.TypeSlideFactory
import org.junit.Assert.*
import org.junit.Test

class SlideSelectTest {

    private val manager = SlideManager()
    private val currentSlide = TypeSlideFactory().createSlide(SlideType.Square)

    @Test
    fun selectSlide_selectTrue_returnTrue() {
        manager.changeSlideStatus(currentSlide, true)
        val actual = currentSlide.isSelect
        assertTrue(actual)
    }

    @Test
    fun selectSlide_selectFalse_returnFalse() {
        manager.changeSlideStatus(currentSlide, false)
        val actual = currentSlide.isSelect
        assertFalse(actual)
    }
}