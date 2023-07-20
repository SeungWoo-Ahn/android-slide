package io.softeer.slideapp.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import io.softeer.slideapp.adapter.ItemTouchHelperCallback
import io.softeer.slideapp.adapter.SlideAdapter
import io.softeer.slideapp.enums.SlideType
import io.softeer.slideapp.manager.SlideManager
import io.softeer.slideapp.model.ImageSlide
import io.softeer.slideapp.model.Slide
import io.softeer.slideapp.util.DoubleClickListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SlideViewModel(
    private val manager: SlideManager = SlideManager()
) : ViewModel() {

    private val _currentSlide = MutableStateFlow<Slide?>(null)
    val currentSlide : StateFlow<Slide?> = _currentSlide
    val slideType = MutableStateFlow<SlideType?>(null)
    val slideHexColor = MutableStateFlow<String?>(null)
    val slideAlpha = MutableStateFlow<Int?>(null)
    val slideSelect = MutableStateFlow(false)
    val slideImgSource = MutableStateFlow<String?>(null)
    val adapter = SlideAdapter(::onSlideClick)
    val itemTouchHelperCallback = ItemTouchHelperCallback(adapter)
    val imageClickListener = object : DoubleClickListener() {
        override fun onOneClick(v: View?) {
            changeSlideStatus(true)
        }

        override fun onDoubleClick(v: View?) {
            pickImage()
        }

    }

    private fun collectSlide(slide:  Slide) {
        slide.let {
            _currentSlide.value = it
            slideType.value = it.type
            slideHexColor.value = it.color.getHexColorStr()
            slideAlpha.value = it.color.alpha
            slideSelect.value = it.isSelect
            if (it is ImageSlide) {
                slideImgSource.value = it.imgSrc
            }
        }
    }

    fun changeSlideStatus(status: Boolean) {
        currentSlide.value?.let {
            collectSlide(manager.changeSlideStatus(it, status))
        }
    }

    fun changeSlideColor() {
        currentSlide.value?.let {
            collectSlide(manager.changeSlideColor(it))
            adapter.notifyCurrentItemChanged()
        }
    }

    fun changeSlideAlpha(plus : Boolean) {
        currentSlide.value?.let {
            if (plus) {
                collectSlide(manager.increaseSlideAlpha(it))
            }
            if (!plus) {
                collectSlide(manager.decreaseSlideAlpha(it))
            }
            adapter.notifyCurrentItemChanged()
        }
    }

    private fun pickImage() {
        Log.i(javaClass.name, "더블 클릭")
    }

    private fun onSlideClick(slide: Slide) {
        collectSlide(slide)
    }

    fun onAddSlide() {
        adapter.addSlide(manager.makeSlide()) {
            collectSlide(it)
        }
    }
}