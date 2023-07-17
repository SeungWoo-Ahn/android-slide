package io.softeer.slideapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.softeer.slideapp.data.RGBColor
import io.softeer.slideapp.data.Size
import io.softeer.slideapp.enum.SlideType
import io.softeer.slideapp.factory.TypeSlideFactory
import io.softeer.slideapp.model.Slide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = TypeSlideFactory()
        val slideList = mutableListOf<Slide>()
        slideList.add(factory.createSlide(SlideType.Rect, Size(216,216), RGBColor(245,0,245,9)))
        slideList.add(factory.createSlide(SlideType.Rect, Size(384,384), RGBColor(43,124,95,5)))
        slideList.add(factory.createSlide(SlideType.Rect, Size(108,108), RGBColor(98,244,15,7)))
        slideList.add(factory.createSlide(SlideType.Rect, Size(233,233), RGBColor(125,39,99,1)))
        for ((index, slide) in slideList.withIndex()) {
            Log.i(javaClass.name, "${slide.type.name}${index+1} $slide")
        }
    }
}