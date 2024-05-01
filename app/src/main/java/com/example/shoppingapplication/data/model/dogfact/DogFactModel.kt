package com.example.shoppingapplication.data.model.dogfact


import com.google.gson.annotations.SerializedName

data class DogFactModel(
    @SerializedName("facts")
    val facts: List<String?>? = listOf(),
    @SerializedName("success")
    val success: Boolean? = false
)