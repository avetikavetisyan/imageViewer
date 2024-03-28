package com.avetik.imageviewer.presentation.view.helpers

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.avetik.imageviewer.R

class RecyclerViewHolder(viewItem: View): RecyclerView.ViewHolder(viewItem) {
    val imageView: ImageView

    init {
        imageView = viewItem.findViewById(R.id.image)
    }
}