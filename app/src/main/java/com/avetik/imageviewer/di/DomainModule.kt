package com.avetik.imageviewer.di

import com.avetik.imageviewer.domain.usecase.ClearImagesTableUseCase
import com.avetik.imageviewer.domain.usecase.GetImageListFromStorageUseCase
import com.avetik.imageviewer.domain.usecase.GetImageListUseCase
import com.avetik.imageviewer.domain.usecase.SaveImagesToStorageUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { ClearImagesTableUseCase(get()) }
    factory { GetImageListFromStorageUseCase(get()) }
    factory { GetImageListUseCase(get()) }
    factory { SaveImagesToStorageUseCase(get()) }
}