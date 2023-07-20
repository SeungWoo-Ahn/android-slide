package io.softeer.slideapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import io.softeer.slideapp.databinding.ActivityMainBinding
import io.softeer.slideapp.viewmodel.SlideViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : SlideViewModel by viewModels()
    private lateinit var launcherResponse: (Intent?) -> Unit
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            launcherResponse(it.data)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bind.viewModel = viewModel
        bind.lifecycleOwner = this
    }

    fun goActivityForResult(intent: Intent, onResponse: (Intent?) -> Unit) {
        launcherResponse = onResponse
        resultLauncher.launch(intent)
    }
}