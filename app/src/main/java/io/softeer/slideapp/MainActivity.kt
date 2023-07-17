package io.softeer.slideapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.softeer.slideapp.factory.TypeSlideFactory

class MainActivity : AppCompatActivity(){

    private val factory = TypeSlideFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}