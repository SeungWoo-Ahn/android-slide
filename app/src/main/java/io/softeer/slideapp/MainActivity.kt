package io.softeer.slideapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import io.softeer.slideapp.enum.SlideType
import io.softeer.slideapp.factory.TypeSlideFactory
import io.softeer.slideapp.model.Slide

class MainActivity : AppCompatActivity(),OnClickListener {

    private val factory = TypeSlideFactory()
    private var clickCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.btn_add_slide)
        button.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val slide = factory.createRectSlide(SlideType.Rect)
        Log.i(javaClass.name, "${slide.type.name}$clickCount $slide")
        clickCount++
    }
}