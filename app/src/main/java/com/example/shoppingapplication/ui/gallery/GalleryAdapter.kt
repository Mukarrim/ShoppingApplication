package com.example.shoppingapplication.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.shoppingapplication.R
import com.example.shoppingapplication.data.model.shoppinglist.ProductItemModel
import com.example.shoppingapplication.databinding.ItemProductBinding

class GalleryAdapter(
    val productList: List<ProductItemModel>,
    val function: (ProductItemModel) -> Unit
) : Adapter<GalleryAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        val binding = ItemProductBinding.bind(itemView)
        fun updateUI(productItemModel: ProductItemModel) {
            // Handle the UI changes based on current data
            binding.apply {
                Glide.with(itemView.context)
                    .load(productItemModel.image)
                    .placeholder(R.drawable.person_svg)
                    .into(ivProfile)
                tvTitle.text = "Title: ${productItemModel.title}"
                tvDescription.text = "Description: ${productItemModel.description}"
                tvCategory.text = "Category: ${productItemModel.category}"
                tvPrice.text = "Price: ${productItemModel.price.toString()}"
                tvRating.text =
                    "Rating: ${productItemModel.rating?.count}(${productItemModel.rating?.rate})"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.updateUI(productList[position])

        holder.binding.root.setOnClickListener {
            function.invoke(productList[position])
        }
    }
}