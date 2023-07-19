package io.softeer.slideapp.viewmodel

import android.graphics.Color
import android.graphics.Rect
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

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