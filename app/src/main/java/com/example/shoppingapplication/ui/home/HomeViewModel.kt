package com.example.shoppingapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapplication.data.api.ApiEndpoints1
import com.example.shoppingapplication.data.api.RetrofitInstance
import com.example.shoppingapplication.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val dataShare = flow<String> {  }
    val dataShareState = MutableStateFlow("Hello") //you need to give default/initial value

    init {
        getDogDetails()
    }

    private fun getDogDetails() {
        viewModelScope.launch {
            val result = repository.getDogFacts()
            if (!result.facts.isNullOrEmpty())
                _text.postValue(result.facts.joinToString())
            dataShareState.emit("World!")
        }
    }
}