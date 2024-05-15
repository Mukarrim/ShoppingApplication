package com.example.shoppingapplication.data.repository

import com.example.shoppingapplication.data.api.ApiEndpoints
import com.example.shoppingapplication.data.api.ApiEndpoints1
import com.example.shoppingapplication.data.api.ApiEndpoints2
import com.example.shoppingapplication.data.model.dogfact.DogFactModel
import com.example.shoppingapplication.data.model.shoppinglist.ProductItemModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiDetails: ApiEndpoints,
    private val apiDetails1: ApiEndpoints1,
    private val apiDetails2: ApiEndpoints2
) : Repository {
    override suspend fun getDogFacts(): DogFactModel = apiDetails.getDogFacts()
//    {
//        return if (2 == 2)
//        //if online
//            apiDetails.getDogFacts()
//        else
//        //if offline
//            DogFactModel() //a local DB reference
//    }

    override suspend fun getProductList(): ArrayList<ProductItemModel> {
        return apiDetails.getProductList()
    }
}