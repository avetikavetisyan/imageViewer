package com.avetik.imageviewer.data

import com.avetik.imageviewer.data.network.models.ImageData
import com.avetik.imageviewer.data.storage.models.ImagesEntity
import com.avetik.imageviewer.domain.models.Image

private const val IMAGE_URL_BASE = "https://live.staticflickr.com/"
private const val BIG_SIZE = "b"
private const val SMALL_SIZE = "m"

fun ImageData.getImage(): Image{
    val urlBase = "$IMAGE_URL_BASE${server}/${id}_${secret}_"
    val urlSmallSize = "$urlBase$SMALL_SIZE.jpg"
    val urlBigSize = "$urlBase$BIG_SIZE.jpg"
    return Image(id = id, imageBigSizeUrl = urlBigSize, imageSmallSizeUrl = urlSmallSize)
}

fun ImagesEntity.getImage(): Image{
    return Image(id = id, imageBigSizeUrl = imageBigSizeUrl, imageSmallSizeUrl = imageSmallSizeUrl)
}

fun Image.getImageEntity(): ImagesEntity {
    return ImagesEntity(id = id, imageBigSizeUrl = imageBigSizeUrl, imageSmallSizeUrl = imageSmallSizeUrl)
}