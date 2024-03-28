package com.avetik.imageviewer.domain.usecase

import com.avetik.imageviewer.domain.models.Image
import com.avetik.imageviewer.domain.repository.ImagesRepository

class SaveImagesToStorageUseCase(private val imagesRepository: ImagesRepository) {
    suspend fun execute(imagesList: List<Image>) {
        imagesRepository.saveImagesToStorage(imagesList)
    }
}