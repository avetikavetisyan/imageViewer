package com.avetik.imageviewer.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avetik.imageviewer.data.storage.models.ImagesEntity

@Dao
interface ImagesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(imagesList: List<ImagesEntity>)

    @Query("SELECT * FROM images")
    suspend fun getAllImages(): List<ImagesEntity>

    @Query("DELETE FROM images")
    fun clearImageTable()
}