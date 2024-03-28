package com.avetik.imageviewer.data.network

import com.avetik.imageviewer.data.network.models.ImagesListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImagesApi {
    @GET("rest/?method=flickr.photos.getRecent&api_key=da9d38d3dee82ec8dda8bb0763bf5d9c&format=json&nojsoncallback=1&per_page=20")
    suspend fun getImagesList(@Query("page") page: Int):Response<ImagesListResponse>?
}