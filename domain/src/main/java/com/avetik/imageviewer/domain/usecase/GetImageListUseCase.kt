package com.avetik.imageviewer.domain.usecase

import com.avetik.imageviewer.domain.models.Images
import com.avetik.imageviewer.domain.repository.ImagesRepository

class GetImageListUseCase(private val imagesRepository: ImagesRepository) {
    suspend fun execute(page: Int): Images {
        return imagesRepository.getImagesList(page = page)
    }
}