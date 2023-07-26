package io.softeer.slideapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.softeer.slideapp.api.RetrofitClient
import io.softeer.slideapp.util.ItemTouchHelperCallback
import io.softeer.slideapp.data.enums.SlideType
import io.softeer.slideapp.data.local.Point
import io.softeer.slideapp.data.model.DrawingSlide
import io.softeer.slideapp.manager.ImageManger
import io.softeer.slideapp.manager.SlideManager
import io.softeer.slideapp.data.model.ImageSlide
import io.softeer.slideapp.data.model.Slide
import io.softeer.slideapp.data.model.SlideWithColor
import io.softeer.slideapp.data.repository.SlideRepositoryImpl
import io.softeer.slideapp.data.repository.local.LocalDB
import io.softeer.slideapp.data.repository.local.LocalDataSource
import io.softeer.slideapp.data.repository.remote.RemoteDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SlideViewModel(
    private val manager: SlideManager = SlideManager(),
    private val imgManager: ImageManger = ImageManger(),
    private val repositoryImpl: SlideRepositoryImpl = SlideRepositoryImpl(
        LocalDataSource(LocalDB()),
        RemoteDataSource(RetrofitClient.slideService)
    )
) : ViewModel() {

    private val _currentSlide = MutableStateFlow<Slide?>(null)
    val currentSlide : StateFlow<Slide?> = _currentSlide
    val slideType = MutableStateFlow<SlideType?>(null)
    val slideHexColor = MutableStateFlow<String?>(null)
    val slideAlpha = MutableStateFlow<Int?>(null)
    val slideSelect = MutableStateFlow(false)
    val slideImgSource = MutableStateFlow<ByteArray?>(null)
    private val slidePoints = MutableStateFlow<List<Point>?>(null)
    val slideEditable = MutableStateFlow(true)
    val adapter = SlideAdapter(repositoryImpl.getAllLocalSlides(),::onSlideClick)
    val itemTouchHelperCallback = ItemTouchHelperCallback(adapter)

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
       addNewSlide(manager.createSlideInstance())
    }

    fun onLoadSlide(): Boolean {
        viewModelScope.launch {
            val remoteSlide = repositoryImpl.getRemoteRandomSlide()
            if (remoteSlide != null) {
                addNewSlide(remoteSlide)
            }
        }
        return true
    }

    private fun addNewSlide(newSlide: Slide?) {
        newSlide?.let {
            repositoryImpl.addLocalSlide(it)
            adapter.addSlide(it, {})
            collectSlide(it)
        }
    }

    fun saveSlidePoints(points: MutableList<Point>) {
        currentSlide.value?.let {
            collectSlide(manager.saveSlidePoints(it, points))
        }
    }
}