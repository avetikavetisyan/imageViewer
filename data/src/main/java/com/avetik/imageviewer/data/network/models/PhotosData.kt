package com.avetik.imageviewer.data.network.models

data class PhotosData(val page: Int, val pages: Int, val perPage: Int, val total:Long, val photo: List<ImageData>)