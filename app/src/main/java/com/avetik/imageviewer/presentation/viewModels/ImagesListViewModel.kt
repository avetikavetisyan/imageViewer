package com.avetik.imageviewer.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avetik.imageviewer.domain.models.Image
import com.avetik.imageviewer.domain.models.Images
import com.avetik.imageviewer.domain.usecase.ClearImagesTableUseCase
import com.avetik.imageviewer.domain.usecase.GetImageListFromStorageUseCase
import com.avetik.imageviewer.domain.usecase.GetImageListUseCase
import com.avetik.imageviewer.domain.usecase.SaveImagesToStorageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ImagesListViewModel(
    private val getImagesList: com.avetik.imageviewer.domain.usecase.GetImageListUseCase,
    private val getImageListFromStorage: com.avetik.imageviewer.domain.usecase.GetImageListFromStorageUseCase,
    private val saveImagesToStorage: com.avetik.imageviewer.domain.usecase.SaveImagesToStorageUseCase,
    private val clearImagesTableUseCase: com.avetik.imageviewer.domain.usecase.ClearImagesTableUseCase
): ViewModel() {
    private var pageNumber = 1
    private var pageCount = 0

    private val _imagesListFlow: MutableStateFlow<List<com.avetik.imageviewer.domain.models.Image>> = MutableStateFlow(emptyList())
    val imagesListFlow: StateFlow<List<com.avetik.imageviewer.domain.models.Image>> = _imagesListFlow

    private val _loadingData: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingData: StateFlow<Boolean> = _loadingData

    private suspend fun getImagesFromNetwork(page: Int): com.avetik.imageviewer.domain.models.Images {
        return getImagesList.execute(page)
    }

    private suspend fun getImagesFromStorage(): com.avetik.imageviewer.domain.models.Images {
        return getImageListFromStorage.execute()
    }

    private fun updateImagesList(isNetworkConnected: Boolean) {
        _loadingData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val images = if(isNetworkConnected) {
                getImagesFromNetwork(pageNumber)
            } else {
                getImagesFromStorage()
            }

            if (images.isSuccess) {
                pageNumber = images.page
                pageCount = images.pages
                _imagesListFlow.value = images.imageList
                if (images.imageList.isNotEmpty() && isNetworkConnected) {
                    clearImagesTableUseCase.execute()
                    saveImagesToStorage.execute(images.imageList)
                }
            }
            _loadingData.value = false
        }
    }

    fun loadImagesList(isNetworkConnected: Boolean){
        if(imagesListFlow.value.isEmpty() && !loadingData.value){
            updateImagesList(isNetworkConnected)
        }
    }

    fun refreshImagesList(isNetworkConnected: Boolean) {
        if(_loadingData.value) {
            return
        }

        if(pageNumber >= pageCount) {
            pageNumber = 1
        } else {
            ++pageNumber
        }
        updateImagesList(isNetworkConnected)
    }
}