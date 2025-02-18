package io.softeer.slideapp.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.softeer.slideapp.util.ItemTouchHelperCallback
import io.softeer.slideapp.data.enums.SlideType
import io.softeer.slideapp.data.local.Point
import io.softeer.slideapp.data.model.DrawingSlide
import io.softeer.slideapp.manager.ImageManger
import io.softeer.slideapp.manager.SlideManager
import io.softeer.slideapp.data.model.ImageSlide
import io.softeer.slideapp.data.model.Slide
import io.softeer.slideapp.data.model.SlideWithColor
import io.softeer.slideapp.data.repository.SlideRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SlideViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val manager: SlideManager,
    private val imgManager: ImageManger,
    private val repository: SlideRepository
) : ViewModel() {


    private val _currentSlide = MutableStateFlow<Slide?>(null)
    val currentSlide : StateFlow<Slide?> = _currentSlide
    val slideType = MutableStateFlow<SlideType?>(null)
    val slideHexColor = MutableStateFlow<String?>(null)
    val slideAlpha = MutableStateFlow<Int?>(null)
    val slideSelect = MutableStateFlow(false)
    val slideImgSource = MutableStateFlow<ByteArray?>(null)
    val slidePoints = MutableStateFlow<List<Point>?>(null)
    val slideEditable = MutableStateFlow(true)
    val adapter = SlideAdapter(::onSlideClick).apply {
        resetAdapter(repository.getAllLocalSlides(), 0)
    }
    val itemTouchHelperCallback = ItemTouchHelperCallback(adapter)
    private var restoreSlides: List<Slide>?
        get() = savedStateHandle[RESTORE_KEY]
        set(value) = savedStateHandle.set(RESTORE_KEY, value)
    private var restoreIndex: Int?
        get() = savedStateHandle[RESTORE_INDEX_KEY]
        set(value) = savedStateHandle.set(RESTORE_INDEX_KEY, value)

    private fun collectSlide(slide: Slide) {
        slide.let {
            _currentSlide.value = it
            slideType.value = it.type
            slideAlpha.value = it.alpha
            slideSelect.value = it.isSelect
            slideHexColor.value = if (it is SlideWithColor) it.getHexColorStr() else null
            slideImgSource.value = if (it is ImageSlide && it.imageSource != null) it.imageSource else null
            slidePoints.value = if (it is DrawingSlide) it.points else null
            slideEditable.value = if (it is DrawingSlide) it.isEditable else false
        }
    }

    fun changeSlideStatus(status: Boolean) {
        currentSlide.value?.let {
            collectSlide(manager.changeSlideStatus(it, status))
        }
    }

    val changeSlideColor = fun() {
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
            else {
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
       addNewSlide(manager.createSlideInstance())
    }

    fun onLoadSlide(): Boolean {
        viewModelScope.launch {
            var remoteSlide = repository.getRemoteRandomSlide()
            if (remoteSlide != null) {
                if (remoteSlide is ImageSlide) {
                    remoteSlide.url?.let {
                        remoteSlide = (remoteSlide as ImageSlide).copy(
                            imageSource = imgManager.getImageByteArrayFromUrl(it)
                        )
                    }
                }
                addNewSlide(remoteSlide)
            }
        }
        return true
    }

    private fun addNewSlide(newSlide: Slide?) {
        newSlide?.let {
            adapter.addSlide(it)
            collectSlide(it)
        }
    }

    fun saveSlidePoints(points: MutableList<Point>) {
        currentSlide.value?.let {
            collectSlide(manager.saveSlidePoints(it, points))
        }
    }

    fun addNewFile() {
        repository.saveSlides(adapter.getSlideList())
        repository.changeFile()
        _currentSlide.value = null
        slideType.value = null
        slideHexColor.value = null
        slideAlpha.value = null
        slideSelect.value = false
        slideImgSource.value = null
        slidePoints.value = null
        slideEditable.value = false
        adapter.resetAdapter(repository.getAllLocalSlides(), 0)
    }

    fun saveSlideList() {
        restoreSlides = adapter.getSlideList()
        restoreIndex = adapter.getCurrentPosition()
    }

    fun restoreSlideList() {
        restoreSlides?.let {
            val position = restoreIndex ?: 0
            collectSlide(it[position])
            adapter.resetAdapter(it, position)
        }
    }

    companion object {
        private const val RESTORE_KEY = "restore_slides"
        private const val RESTORE_INDEX_KEY = "restore_index"
    }
}