package com.avetik.imageviewer.app

import android.app.Application
import androidx.room.Room
import com.avetik.imageviewer.data.storage.AppDatabase
import com.avetik.imageviewer.di.appModule
import com.avetik.imageviewer.di.dataModule
import com.avetik.imageviewer.di.domainModule
import org.koin.core.context.startKoin

private const val DATA_BASE_NAME = "images_data_base"

class App: Application() {
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            DATA_BASE_NAME
        ).build()

        startKoin {
            modules(listOf(appModule, domainModule, dataModule))
        }
    }
}