package com.avetik.imageviewer.domain.repository

import com.avetik.imageviewer.domain.models.Image
import com.avetik.imageviewer.domain.models.Images

interface ImagesRepository {
    suspend fun getImagesList(page: Int): Images
    suspend fun getImagesFromStorage(): Images
    suspend fun saveImagesToStorage(imagesList: List<Image>)
    suspend fun clearImageTable()
}