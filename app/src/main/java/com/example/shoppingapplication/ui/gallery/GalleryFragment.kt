package com.example.shoppingapplication.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapplication.R
import com.example.shoppingapplication.data.model.shoppinglist.ProductItemModel
import com.example.shoppingapplication.databinding.FragmentGalleryBinding
import com.example.shoppingapplication.util.ResponseHandling
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.apply {
            galleryViewModel.productList.observe(viewLifecycleOwner) {
                // handle the product list
                when (it) {
                    is ResponseHandling.Failure -> {
                        // Failure
                        textGallery.text = it.error
                        textGallery.visibility = View.VISIBLE
                        rvProducts.visibility = View.GONE
                        progressBar.visibility = View.GONE
                    }

                    is ResponseHandling.Success<*> -> {
                        // Success
                        setupUI(it.data as ArrayList<ProductItemModel>)
                        textGallery.visibility = View.GONE
                        rvProducts.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                    }

                    else -> {
                        // Loading
                        progressBar.visibility = View.VISIBLE
                        textGallery.visibility = View.GONE
                        rvProducts.visibility = View.GONE
                    }
                }
            }
        }
        return root
    }

    private fun FragmentGalleryBinding.setupUI(it: ArrayList<ProductItemModel>) {
        // Recyclerview setup
        // Layout manager
        // Adapter
        rvProducts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = GalleryAdapter(it) { productItemModel ->
                findNavController().navigate(
                    R.id.action_nav_gallery_to_detailFragment,
                    bundleOf(
                        Pair("title", productItemModel.title),
                        Pair("desc", productItemModel.description),
                        Pair("image", productItemModel.image)
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}