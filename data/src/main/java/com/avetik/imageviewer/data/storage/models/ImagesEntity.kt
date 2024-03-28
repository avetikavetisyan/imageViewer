package com.avetik.imageviewer.data.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImagesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val imageBigSizeUrl: String,
    val imageSmallSizeUrl: String
)
