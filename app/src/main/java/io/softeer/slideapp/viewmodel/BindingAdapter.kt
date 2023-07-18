package io.softeer.slideapp.viewmodel

import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("slideColor")
fun setSlideBackground(view: View, hexColor: String) {
    view.setBackgroundColor(Color.parseColor("#$hexColor"))
}