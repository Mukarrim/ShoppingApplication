package com.example.shoppingapplication.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapplication.data.api.RetrofitInstance
import com.example.shoppingapplication.data.model.shoppinglist.ProductItemModel
import com.example.shoppingapplication.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    private val _productList = MutableLiveData<ArrayList<ProductItemModel>>()
    val productList: LiveData<ArrayList<ProductItemModel>> = _productList

    init {
        getProductList()
    }

    fun getProductList() {
        viewModelScope.launch {

            val result = repository.getProductList()

            if (!result.isNullOrEmpty()) {
                _productList.postValue(result)
            }

        }
    }
}