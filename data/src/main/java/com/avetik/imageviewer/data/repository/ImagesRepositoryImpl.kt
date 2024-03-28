package com.avetik.imageviewer.data.repository

import android.util.Log
import com.avetik.imageviewer.data.getImage
import com.avetik.imageviewer.data.getImageEntity
import com.avetik.imageviewer.data.network.models.ImageData
import com.avetik.imageviewer.data.network.ImagesApi
import com.avetik.imageviewer.data.storage.ImagesDao
import com.avetik.imageviewer.domain.models.Image
import com.avetik.imageviewer.domain.models.Images
import com.avetik.imageviewer.domain.repository.ImagesRepository

private const val STAT_OK = "ok"
private const val TAG = "ImagesRepository"

class ImagesRepositoryImpl(private val imagesApi: ImagesApi, private val imagesDao: ImagesDao): ImagesRepository {
    override suspend fun getImagesList(page: Int):Images {
        try {
            val response = imagesApi.getImagesList(page)
            if(response == null || !response.isSuccessful) {
                return Images(page = 0, pages = 0, isSuccess = false, imageList = emptyList())
            }

            val result = response.body()

            return if(result == null || result.stat != STAT_OK || result.photos == null){
                Images(page = 0, pages = 0, isSuccess = false, imageList = emptyList())
            } else {
                Images(page = result.photos.page, pages = result.photos.pages, isSuccess = true, imageList = mapToImages(result.photos.photo))
            }
        } catch (e: Exception){
            Log.d(TAG, "error message = ${e.message}")
            return Images(page = 0, pages = 0, isSuccess = false, imageList = emptyList())
        }
    }

    override suspend fun getImagesFromStorage(): Images {
        return Images(1, 1, true, imagesDao.getAllImages().map { it.getImage() })
    }

    override suspend fun saveImagesToStorage(imagesList: List<Image>) {
        imagesDao.insertImages(imagesList.map { it.getImageEntity() })
    }

    override suspend fun clearImageTable() {
        imagesDao.clearImageTable()
    }

    private fun mapToImages(imageDataList: List<ImageData>):List<Image> {
        return imageDataList.map { it.getImage() }
    }
}