package com.example.shoppingapplication.data.api

object ApiDetail {

    // API: https://dog-api.kinduff.com/api/facts

    const val BASE_URL = "https://dog-api.kinduff.com/"
    const val DOG_FACT_ENDPOINT = "api/facts"

    // We do not separate BASE URL if already defined
    // -> because the retrofit instance already has a reference of base url

//    // Base URL -> https://fakestoreapi.com/
//    // Endpoint -> products
//    const val BASE_URL_PRODUCT = "https://fakestoreapi.com/"
//    const val PRODUCT_LIST_ENDPOINT = "products"
//    const val PRODUCT_ENDPOINT = "products/list"
//    const val PRODUCT_ENDPOINT1 = "product/category"
//
////    const val BASE_URL_PRODUCT = "https://fakestoreapi.com/"
    const val PRODUCT_LIST_ENDPOINT = "https://fakestoreapi.com/products"

}