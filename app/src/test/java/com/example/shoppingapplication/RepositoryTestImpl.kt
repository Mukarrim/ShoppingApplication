package com.example.shoppingapplication

import com.example.shoppingapplication.data.model.dogfact.DogFactModel
import com.example.shoppingapplication.data.model.shoppinglist.ProductItemModel
import com.example.shoppingapplication.data.repository.Repository

class RepositoryTestImpl : Repository {
    override suspend fun getDogFacts(): DogFactModel {
        return DogFactModel(listOf(), success = false)
    }

    override suspend fun getProductList(): ArrayList<ProductItemModel> {
        return arrayListOf<ProductItemModel>()
    }
}