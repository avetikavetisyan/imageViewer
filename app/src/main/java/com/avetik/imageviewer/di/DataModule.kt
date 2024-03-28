package com.avetik.imageviewer.di

import com.avetik.imageviewer.app.App
import com.avetik.imageviewer.data.network.ImagesApi
import com.avetik.imageviewer.data.network.RetrofitClientInstance
import com.avetik.imageviewer.data.repository.ImagesRepositoryImpl
import com.avetik.imageviewer.domain.repository.ImagesRepository
import org.koin.dsl.module

val dataModule = module {

    single<ImagesApi> { RetrofitClientInstance().imagesApi }

    factory<ImagesRepository> {
        ImagesRepositoryImpl(
            get(),
            App.database.imageDao()
        )
    }
}