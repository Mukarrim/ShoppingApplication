package com.example.shoppingapplication.data.api

import com.example.shoppingapplication.data.model.dogfact.DogFactModel
import com.example.shoppingapplication.data.model.shoppinglist.ProductItemModel
import retrofit2.http.GET

interface ApiEndpoints1 {

    @GET(ApiDetail.DOG_FACT_ENDPOINT)
    suspend fun getDogFacts(): DogFactModel

    @GET(ApiDetail.PRODUCT_LIST_ENDPOINT)
    suspend fun getProductList(): ArrayList<ProductItemModel>

}