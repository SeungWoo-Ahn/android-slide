package io.softeer.slideapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.SavedStateHandle
import io.softeer.slideapp.R
import io.softeer.slideapp.api.RetrofitClient
import io.softeer.slideapp.data.repository.SlideRepositoryImpl
import io.softeer.slideapp.data.repository.local.LocalDB
import io.softeer.slideapp.data.repository.local.LocalDataSource
import io.softeer.slideapp.data.repository.remote.RemoteDataSource
import io.softeer.slideapp.databinding.ActivityMainBinding
import io.softeer.slideapp.manager.ImageManger
import io.softeer.slideapp.manager.SlideManager
import io.softeer.slideapp.util.DoubleClickListener
import io.softeer.slideapp.util.SlideViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: SlideViewModel by viewModels {
        SlideViewModelFactory(
            savedStateHandle = SavedStateHandle(),
            manager = SlideManager(),
            imgManager = ImageManger(),
            repository = SlideRepositoryImpl(
                LocalDataSource(LocalDB()),
                RemoteDataSource(RetrofitClient.slideService)
            )
        )
    }
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
    private lateinit var bind : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_main)
        bind.apply {
            viewModel = this@MainActivity.viewModel
            activity = this@MainActivity
            lifecycleOwner = this@MainActivity
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.restoreSlideList()
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveSlideList()
    }

    fun goActivityForResult(intent: Intent, onResponse: (Intent?) -> Unit) {
        launcherResponse = onResponse
        resultLauncher.launch(intent)
    }
}