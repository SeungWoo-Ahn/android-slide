package io.softeer.slideapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import io.softeer.slideapp.databinding.ActivityMainBinding
import io.softeer.slideapp.viewmodel.SlideViewModel

class MainActivity : AppCompatActivity(){

    private val viewModel : SlideViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bind.apply {

        }
    }
}