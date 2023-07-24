package io.softeer.slideapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import io.softeer.slideapp.R
import io.softeer.slideapp.databinding.ActivityMainBinding
import io.softeer.slideapp.util.DoubleClickListener

class MainActivity : AppCompatActivity() {

    private val viewModel : SlideViewModel by viewModels()
    private lateinit var launcherResponse: (Intent?) -> Unit
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            launcherResponse(it.data)
        }
    }
    val imageClickListener = object : DoubleClickListener() {
        override fun onOneClick(v: View?) {
            viewModel.changeSlideStatus(true)
        }

        override fun onDoubleClick(v: View?) {
            viewModel.pickImage(this@MainActivity) {
                it?.let { source ->
                    viewModel.changeSlideImage(source)
                    viewModel.adapter.notifyCurrentItemChanged()
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind : ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        bind.viewModel = viewModel
        bind.activity = this
        bind.lifecycleOwner = this
    }

    fun goActivityForResult(intent: Intent, onResponse: (Intent?) -> Unit) {
        launcherResponse = onResponse
        resultLauncher.launch(intent)
    }
}