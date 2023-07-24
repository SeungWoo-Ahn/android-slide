package io.softeer.slideapp.viewmodel

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import io.softeer.slideapp.R
import io.softeer.slideapp.adapter.ItemTouchHelperCallback
import io.softeer.slideapp.util.DoubleClickListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@BindingAdapter("isSelect")
fun setSelectStatus(view: View, status: Boolean) {
    view.isSelected = status
}

@BindingAdapter("slideColor")
fun setSlideBackground(view: View, hexColor: String?) {
    if (hexColor == null) {
        view.setBackgroundColor(ContextCompat.getColor(view.context, R.color.white))
        return
    }
    view.setBackgroundColor(Color.parseColor("#$hexColor"))
}

@BindingAdapter("itemDecoration")
fun setRecyclerItemDecoration(view: RecyclerView, vertical: Int) {
    view.addItemDecoration(
        object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.bottom = vertical
            }
        }
    )
}

@BindingAdapter("itemHelper")
fun setRecyclerItemHelper(view: RecyclerView, helperCallback: ItemTouchHelperCallback) {
    val helper = ItemTouchHelper(helperCallback)
    helper.attachToRecyclerView(view)
}

@BindingAdapter("onDoubleClick")
fun setDoubleClickListener(view: View, doubleClickListener: DoubleClickListener) {
    view.setOnClickListener(doubleClickListener)
}

@BindingAdapter("slideImg", "imgAlpha")
fun setSlideImage(view: ImageView, source: ByteArray?, alpha: Int?) {

    if (source == null) {
        view.setImageDrawable(
            AppCompatResources.getDrawable(
                view.context,
                R.drawable.ic_image
            )
        )
        return
    }

    CoroutineScope(Dispatchers.IO).launch {
        Glide.with(view)
            .asBitmap()
            .load(source)
            .apply {
            CoroutineScope(Dispatchers.Main).launch {
                into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        val width = resource.width
                        val height = resource.height
                        val resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
                        val canvas = Canvas(resultBitmap)
                        val paint = Paint()
                        val colorMatrix = ColorMatrix()
                        colorMatrix.setSaturation(1f)
                        val alphaScale = if (alpha != null) alpha / 10f else 1f
                        colorMatrix.set(floatArrayOf(
                            1f, 0f, 0f, 0f, 0f,
                            0f, 1f, 0f, 0f, 0f,
                            0f, 0f, 1f, 0f, 0f,
                            0f, 0f, 0f, alphaScale, 0f
                        ))
                        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
                        canvas.drawBitmap(resource, 0f, 0f, paint)
                        view.setImageBitmap(resultBitmap)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                    }

                })
            }
        }
    }
}