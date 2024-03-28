package com.avetik.imageviewer.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.avetik.imageviewer.R
import com.avetik.imageviewer.databinding.FragmentDetailsBinding
import com.avetik.imageviewer.presentation.delegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val binding by viewBinding(FragmentDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString(IMAGE_URL_KEY)
        if(!url.isNullOrEmpty()) {
            Glide.with(view.context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerInside()
                .into(binding.imageView)
        }
    }
}