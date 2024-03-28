package com.avetik.imageviewer.presentation.view

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.avetik.imageviewer.R
import com.avetik.imageviewer.databinding.FragmentImagesListBinding
import com.avetik.imageviewer.presentation.view.helpers.ImagesViewsAdapter
import com.avetik.imageviewer.presentation.view.helpers.SpacesItemDecoration
import com.avetik.imageviewer.presentation.delegate.viewBinding
import com.avetik.imageviewer.presentation.viewModels.ImagesListViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

const val IMAGE_URL_KEY = "image_url"

class ImagesListFragment : Fragment(R.layout.fragment_images_list) {
    private val binding by viewBinding(FragmentImagesListBinding::bind)
    private val vm: ImagesListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = "Images List"

        val spanCount = getSpanCount(view.context)
        binding.imagesViews.layoutManager = GridLayoutManager(context, spanCount)
        binding.imagesViews.addItemDecoration(SpacesItemDecoration())
        binding.imagesViews.setHasFixedSize(true)
        val adapter = ImagesViewsAdapter(::navigateToDetailsFragment)
        binding.imagesViews.adapter = adapter

        vm.loadingData.onEach {
            binding.swipeRefreshLayout.isRefreshing = it
        }.launchIn(lifecycle.coroutineScope)

        vm.imagesListFlow.onEach {
            adapter.updateData(it)
        }.launchIn(lifecycle.coroutineScope)
        vm.loadImagesList(isNetworkConnected(binding.root.context))

        binding.swipeRefreshLayout.setOnRefreshListener {
            vm.refreshImagesList(isNetworkConnected(binding.root.context))
        }
    }

    private fun navigateToDetailsFragment(imageUrl: String, id: Long) {
        val bundle = bundleOf(IMAGE_URL_KEY to imageUrl, "id" to id )
        activity?.findNavController(R.id.nav_host_fragment_content_main)?.apply {
            navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
    }

    private fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
            .isConnected
    }

    private fun getSpanCount(context: Context): Int{
        val currentOrientation = context.resources.configuration.orientation
        return if(currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            4
        } else {
            2
        }
    }
}