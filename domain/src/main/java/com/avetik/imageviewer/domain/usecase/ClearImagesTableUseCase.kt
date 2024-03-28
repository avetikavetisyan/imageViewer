package com.avetik.imageviewer.domain.usecase

import com.avetik.imageviewer.domain.repository.ImagesRepository

class ClearImagesTableUseCase(private val imagesRepository: ImagesRepository) {
    suspend fun execute() {
        return imagesRepository.clearImageTable()
    }
}