package com.avetik.imageviewer.domain.models

data class Images(val page: Int, val pages: Int, val isSuccess: Boolean, val imageList: List<Image> )