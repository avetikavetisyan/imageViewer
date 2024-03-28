package com.avetik.imageviewer.domain.usecase

import com.avetik.imageviewer.domain.models.Images
import com.avetik.imageviewer.domain.repository.ImagesRepository

class GetImageListFromStorageUseCase(private val imagesRepository: ImagesRepository) {
    suspend fun execute(): Images {
        return imagesRepository.getImagesFromStorage()
    }
}