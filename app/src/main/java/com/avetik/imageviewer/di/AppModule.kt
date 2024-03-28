package com.avetik.imageviewer.di

import com.avetik.imageviewer.presentation.viewModels.ImagesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel<ImagesListViewModel>{
        ImagesListViewModel(get(), get(), get(), get())
    }
}