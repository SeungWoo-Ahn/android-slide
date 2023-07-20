package io.softeer.slideapp.viewmodel

import android.graphics.Color
import android.graphics.Rect
import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import io.softeer.slideapp.R
import io.softeer.slideapp.adapter.ItemTouchHelperCallback
import io.softeer.slideapp.util.DoubleClickListener

@BindingAdapter("isSelect")
fun setSelectStatus(view: View, status: Boolean) {
    view.isSelected = status
}

@BindingAdapter("slideColor")
fun setSlideBackground(view: View, hexColor: String?) {
    hexColor?.let {
        view.setBackgroundColor(Color.parseColor("#$it"))
    }
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

@BindingAdapter("slideImg")
fun setSlideImage(view: ImageView, source: String?) {
    if (source.isNullOrEmpty()) {
        view.setImageDrawable(
            AppCompatResources.getDrawable(
                view.context,
                R.drawable.ic_image
            )
        )
        return
    }
}