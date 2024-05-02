package com.example.shoppingapplication.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.shoppingapplication.R
import com.example.shoppingapplication.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        // arguments -> default functionality to hanlde any bundles passed from previous fragments
        val title = arguments?.getString("title")
        val description = arguments?.getString("desc")
        val image = arguments?.getString("image")

        binding.apply {
            tvTitle.text = title
            tvDescription.text = description
            Glide.with(requireContext()).load(image).into(ivProfile)
        }

        return binding.root
    }

}