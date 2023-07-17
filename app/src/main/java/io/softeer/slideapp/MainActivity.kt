package io.softeer.slideapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import io.softeer.slideapp.databinding.ActivityMainBinding
import io.softeer.slideapp.factory.TypeSlideFactory
import io.softeer.slideapp.viewmodel.SlideManager

class MainActivity : AppCompatActivity(){

    private val manager : SlideManager by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}