package io.softeer.slideapp.util

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.softeer.slideapp.data.repository.SlideRepository
import io.softeer.slideapp.manager.ImageManger
import io.softeer.slideapp.manager.SlideManager
import io.softeer.slideapp.ui.SlideViewModel

class SlideViewModelFactory(
    private val savedStateHandle: SavedStateHandle,
    private val manager: SlideManager,
    private val imgManager: ImageManger,
    private val repository: SlideRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SlideViewModel::class.java)) {
            return SlideViewModel(savedStateHandle, manager, imgManager, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}