package com.example.shoppingapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapplication.data.api.RetrofitInstance
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    init {
        getDogDetails()
    }

    private fun getDogDetails() {
        viewModelScope.launch {
            val result = RetrofitInstance.apiClient.getDogFacts()
            if (!result.facts.isNullOrEmpty())
                _text.postValue(result.facts.joinToString())
        }
    }
}