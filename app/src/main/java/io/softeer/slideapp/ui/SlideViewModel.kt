package io.softeer.slideapp.ui

import androidx.lifecycle.ViewModel
<<<<<<< HEAD
import androidx.lifecycle.viewModelScope
import io.softeer.slideapp.api.RetrofitClient
=======
<<<<<<<< HEAD:app/src/main/java/io/softeer/slideapp/ui/SlideViewModel.kt
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
import io.softeer.slideapp.util.ItemTouchHelperCallback
import io.softeer.slideapp.data.enums.SlideType
import io.softeer.slideapp.manager.ImageManger
import io.softeer.slideapp.manager.SlideManager
import io.softeer.slideapp.data.model.ImageSlide
import io.softeer.slideapp.data.model.Slide
import io.softeer.slideapp.data.model.SquareSlide
<<<<<<< HEAD
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
=======
========
import io.softeer.slideapp.adapter.ItemTouchHelperCallback
import io.softeer.slideapp.adapter.SlideAdapter
import io.softeer.slideapp.enums.SlideType
import io.softeer.slideapp.manager.SlideManager
import io.softeer.slideapp.model.Slide
import io.softeer.slideapp.model.SquareSlide
>>>>>>>> f375d22 (Fix : 폴더 구조 변경):app/src/main/java/io/softeer/slideapp/viewmodel/SlideViewModel.kt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SlideViewModel(
    private val manager: SlideManager = SlideManager()
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
) : ViewModel() {

    private val _currentSlide = MutableStateFlow<Slide?>(null)
    val currentSlide : StateFlow<Slide?> = _currentSlide
    val slideType = MutableStateFlow<SlideType?>(null)
    val slideHexColor = MutableStateFlow<String?>(null)
    val slideAlpha = MutableStateFlow<Int?>(null)
    val slideSelect = MutableStateFlow(false)
<<<<<<< HEAD
    val slideImgSource = MutableStateFlow<ByteArray?>(null)
    val adapter = SlideAdapter(repositoryImpl.getAllLocalSlides(),::onSlideClick)
=======
    val adapter = SlideAdapter(::onSlideClick)
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
    val itemTouchHelperCallback = ItemTouchHelperCallback(adapter)

    private fun collectSlide(slide: Slide) {
        slide.let {
            _currentSlide.value = it
            slideType.value = it.type
            slideAlpha.value = it.alpha
            slideSelect.value = it.isSelect
<<<<<<< HEAD
            slideHexColor.value = if (it is SquareSlide) it.getHexColorStr() else null
            slideImgSource.value = if (it is ImageSlide && it.imageSource != null) it.imageSource else null
=======
<<<<<<< HEAD
=======
            slideHexColor.value = if (it is SquareSlide) it.getHexColorStr() else null
            slideImgSource.value = if (it is ImageSlide && it.imageSource != null) it.imageSource!!.imgBinary else null
>>>>>>> d526cc2 (Feat : Retrofit 사용 준비, Fix : dataclass 수정)
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
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
<<<<<<< HEAD
            } else {
=======
            }
            else {
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
                collectSlide(manager.decreaseSlideAlpha(it))
            }
            adapter.notifyCurrentItemChanged()
        }
    }

<<<<<<< HEAD
    fun pickImage(activity: MainActivity, onSuccess: (ByteArray?) -> Unit) {
        imgManager.pickImageFromGallery(activity, onSuccess)
    }

    fun changeSlideImage(imageByteArray: ByteArray) {
        currentSlide.value?.let {
            collectSlide(manager.changeSlideImageSource(it, imageByteArray))
        }
    }

=======
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
    private fun onSlideClick(slide: Slide) {
        collectSlide(slide)
    }

    fun onAddSlide() {
<<<<<<< HEAD
       addNewSlide(manager.createSlideInstance())
    }

    fun onLoadSlide(): Boolean {
        viewModelScope.launch {
            var remoteSlide = repositoryImpl.getRemoteRandomSlide()
            if (remoteSlide != null) {
                if (remoteSlide is ImageSlide) {
                    remoteSlide.url?.let {
                        remoteSlide = (remoteSlide as ImageSlide).copy(
                            imageSource = imgManager.getImageByteArrayFromUrl(it)
                        )
                    }
                }
                addNewSlide(remoteSlide!!)
            }
        }
        return true
    }

    private fun addNewSlide(newSlide: Slide) {
        newSlide.let {
            repositoryImpl.addLocalSlide(it)
            adapter.addSlide()
            collectSlide(it)
        }
    }
=======
        adapter.addSlide(manager.createSlideInstance()) {
            collectSlide(it)
        }
    }

    fun onLoadSlide(): Boolean {
        return true
    }
>>>>>>> f375d22 (Fix : 폴더 구조 변경)
}