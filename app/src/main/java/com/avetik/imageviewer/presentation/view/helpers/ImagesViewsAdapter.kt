package com.avetik.imageviewer.presentation.view.helpers

import android.util.SizeF
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avetik.imageviewer.R
import com.avetik.imageviewer.domain.models.Image
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ImagesViewsAdapter(
    private val listener: (url: String, id: Long) -> Unit
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    private var imageList: List<Image> = emptyList()
    private var viewHolderSize = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        if (viewHolderSize == 0) {
            calculateViewHolderSize(SizeF(parent.width.toFloat(), parent.height.toFloat()))
        }
        setViewSize(view)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val image = imageList[position]
        Glide.with(holder.imageView.context)
            .load(image.imageSmallSizeUrl)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(R.drawable.image_placeholder)
            .centerCrop()
            .into(holder.imageView)
        holder.imageView.setOnClickListener {
            listener.invoke(image.imageBigSizeUrl, image.id)
        }
    }

    fun updateData(list: List<Image>){
        imageList = list
        notifyDataSetChanged()
    }

    private fun setViewSize(view: View){
        view.layoutParams.width = viewHolderSize
        view.layoutParams.height = viewHolderSize
    }

    private fun calculateViewHolderSize(screenSize: SizeF){
        viewHolderSize = if(screenSize.width > screenSize.height) {
            (screenSize.width * 0.2f).toInt()
        } else {
            (screenSize.width * 0.35f).toInt()
        }
    }
}