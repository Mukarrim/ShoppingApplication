package com.example.shoppingapplication.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapplication.data.api.ApiEndpoints
import com.example.shoppingapplication.data.api.RetrofitInstance
import com.example.shoppingapplication.data.model.shoppinglist.ProductItemModel
import com.example.shoppingapplication.data.repository.Repository
import com.example.shoppingapplication.util.ResponseHandling
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _productList = MutableLiveData<ResponseHandling>()
    val productList: LiveData<ResponseHandling> = _productList

    init {
        getProductList()
    }

    fun getProductList() {
        viewModelScope.launch {
            _productList.postValue(ResponseHandling.Loading)

            val result = repository.getProductList()

            if (result.isNotEmpty()) {
                _productList.postValue(ResponseHandling.Success(result))
            } else {
                _productList.postValue(ResponseHandling.Failure("No results found!"))
            }

        }
    }
}