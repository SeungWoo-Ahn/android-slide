package io.softeer.slideapp.viewmodel

import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter
import io.softeer.slideapp.data.RGBColor

@BindingAdapter("isSelect")
fun setIsSelect(view : View, status : Boolean) {
    view.isSelected = status
}

@BindingAdapter("slideColor")
fun setSlideBackground(view: View, hexColor : String) {
    view.setBackgroundColor(Color.parseColor("#$hexColor"))
}