package io.softeer.slideapp.viewmodel

import androidx.lifecycle.ViewModel
import io.softeer.slideapp.MainActivity
import io.softeer.slideapp.adapter.ItemTouchHelperCallback
import io.softeer.slideapp.adapter.SlideAdapter
import io.softeer.slideapp.enums.SlideType
import io.softeer.slideapp.manager.ImageManger
import io.softeer.slideapp.manager.SlideManager
import io.softeer.slideapp.model.ImageSlide
import io.softeer.slideapp.model.Slide
import io.softeer.slideapp.model.SquareSlide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SlideViewModel(
    private val manager: SlideManager = SlideManager(),
    private val imgManager: ImageManger = ImageManger()
) : ViewModel() {

    private val _currentSlide = MutableStateFlow<Slide?>(null)
    val currentSlide : StateFlow<Slide?> = _currentSlide
    val slideType = MutableStateFlow<SlideType?>(null)
    val slideHexColor = MutableStateFlow<String?>(null)
    val slideAlpha = MutableStateFlow<Int?>(null)
    val slideSelect = MutableStateFlow(false)
    val slideImgSource = MutableStateFlow<ByteArray?>(null)
    val adapter = SlideAdapter(::onSlideClick)
    val itemTouchHelperCallback = ItemTouchHelperCallback(adapter)

    private fun collectSlide(slide:  Slide) {
        slide.let {
            _currentSlide.value = it
            slideType.value = it.type
            slideAlpha.value = it.alpha
            slideSelect.value = it.isSelect
            slideHexColor.value = if (it is SquareSlide) it.getHexColorStr() else null
            slideImgSource.value = if (it is ImageSlide && it.imageSource != null) it.imageSource!!.imgBinary else null
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
            } else {
                collectSlide(manager.decreaseSlideAlpha(it))
            }
            adapter.notifyCurrentItemChanged()
        }
    }

    fun pickImage(activity: MainActivity, onSuccess: (ByteArray?) -> Unit) {
        imgManager.pickImageFromGallery(activity, onSuccess)
    }

    fun changeSlideImage(imageByteArray: ByteArray) {
        currentSlide.value?.let {
            collectSlide(manager.changeSlideImageSource(it, imageByteArray))
        }
    }

    private fun onSlideClick(slide: Slide) {
        collectSlide(slide)
    }

    fun onAddSlide() {
        adapter.addSlide(manager.createSlideInstance()) {
            collectSlide(it)
        }
    }

    fun onLoadSlide(): Boolean {
        return true
    }
}