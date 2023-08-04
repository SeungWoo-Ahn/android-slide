package io.softeer.slideapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import io.softeer.slideapp.R
import io.softeer.slideapp.databinding.ActivityMainBinding
import io.softeer.slideapp.util.DoubleClickListener
import kotlinx.coroutines.flow.collect

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
            pickImageFromGallery {
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

    private fun launchActivityForResult(intent: Intent, onResponse: (Intent?) -> Unit) {
        launcherResponse = onResponse
        resultLauncher.launch(intent)
    }

    private fun pickImageFromGallery(
        onSuccess: (ByteArray?) -> Unit
    ) {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        launchActivityForResult(intent) { imageUri ->
            imageUri?.data?.let {
                onSuccess(imageUriToByteArray(it))
            }
        }
    }

    @SuppressLint("Recycle")
    private fun imageUriToByteArray(imageUri: Uri): ByteArray {
        val inputStream = contentResolver.openInputStream(imageUri)
        return inputStream?.readBytes() ?: byteArrayOf()
    }
}