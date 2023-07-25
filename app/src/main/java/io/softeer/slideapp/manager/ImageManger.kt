package io.softeer.slideapp.manager

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import io.softeer.slideapp.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.net.URL

class ImageManger {

    fun pickImageFromGallery(
        activity: MainActivity,
        onSuccess: (ByteArray?) -> Unit
    ) {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        activity.goActivityForResult(intent) {
            val imageUri = it?.data
            if (imageUri != null) {
                onSuccess(imageUriToByteArray(activity, imageUri))
            }
        }

    }

    suspend fun getImageByteArrayFromUrl(urlString: String): ByteArray? {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL(urlString)
                val connection = url.openConnection()
                connection.doInput = true
                connection.connect()
                val inputStream = connection.getInputStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)
                val byteArrayOutputStream = ByteArrayOutputStream()
                bitmap?.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                byteArrayOutputStream.toByteArray()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    @SuppressLint("Recycle")
    private fun imageUriToByteArray(activity: MainActivity, imageUri: Uri): ByteArray {
        val inputStream = activity.contentResolver.openInputStream(imageUri)
        return inputStream?.readBytes() ?: byteArrayOf()
    }
}