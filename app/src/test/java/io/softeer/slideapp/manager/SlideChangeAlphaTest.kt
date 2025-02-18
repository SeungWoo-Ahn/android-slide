package io.softeer.slideapp.manager

import io.softeer.slideapp.data.enums.SlideType
import io.softeer.slideapp.data.factory.TypeSlideFactory
import io.softeer.slideapp.data.model.Slide
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SlideChangeAlphaTest{

    private val manager = SlideManager()
    private lateinit var currentSlide : Slide

    @Before
    fun setUp() {
        currentSlide = TypeSlideFactory().createSlide(SlideType.Square)
    }

    @Test
    fun changeSlideAlpha_increaseAlpha_increaseOne() {
        currentSlide.color.alpha = 7
        val expected = 8
        manager.increaseSlideAlpha(currentSlide)
        val actual = currentSlide.color.alpha
        assertEquals(expected, actual)
    }


    @Test
    fun changeSlideAlpha_increaseAlpha_increaseNone() {
        currentSlide.color.alpha = 10
        val expected = 10
        manager.increaseSlideAlpha(currentSlide)
        val actual = currentSlide.color.alpha
        assertEquals(expected, actual)
    }

    @Test
    fun changeSlideAlpha_decreaseAlpha_decreaseOne() {
        currentSlide.color.alpha = 10
        val expected = 9
        manager.decreaseSlideAlpha(currentSlide)
        val actual = currentSlide.color.alpha
        assertEquals(expected, actual)
    }

    @Test
    fun changeSlideAlpha_decreaseAlpha_decreaseNone() {
        currentSlide.color.alpha = 1
        val expected = 1
        manager.decreaseSlideAlpha(currentSlide)
        val actual = currentSlide.color.alpha
        assertEquals(expected, actual)
    }

}