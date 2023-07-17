package io.softeer.slideapp.viewmodel

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isSelect")
fun setIsSelect(view : View, status : Boolean) {
    view.isSelected = status
}