package com.example.shoppingapplication.data.api

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val converter = GsonConverterFactory.create()
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiDetail.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(converter)
        .build()

    // Create reference to our local API Endpoints
    val apiClient = retrofit.create(ApiEndpoints::class.java)

}