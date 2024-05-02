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
import com.example.shoppingapplication.databinding.FragmentGalleryBinding

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
//            textGallery.setOnClickListener {
//                findNavController().navigate(R.id.action_nav_gallery_to_listFragment)
//            }
            galleryViewModel.productList.observe(viewLifecycleOwner) {
                // handle the product list

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
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}