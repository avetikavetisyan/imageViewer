package com.avetik.imageviewer.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.flickr.com/services/"

class RetrofitClientInstance {
    val imagesApi: ImagesApi = initRetrofitInstance()

    private fun initRetrofitInstance(): ImagesApi {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ImagesApi::class.java)
    }
}