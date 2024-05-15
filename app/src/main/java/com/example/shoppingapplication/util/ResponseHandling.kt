package com.example.shoppingapplication.util

sealed class ResponseHandling {
    data object Loading : ResponseHandling()
    data class Success<Any>(val data: Any) : ResponseHandling()

    data class Failure(val error: String) : ResponseHandling()
}