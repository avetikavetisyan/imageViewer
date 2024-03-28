package com.avetik.imageviewer.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.avetik.imageviewer.data.storage.models.ImagesEntity

@Database(entities = [ImagesEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun imageDao(): ImagesDao
}