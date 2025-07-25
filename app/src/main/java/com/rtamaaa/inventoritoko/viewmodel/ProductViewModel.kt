package com.rtamaaa.inventoritoko.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rtamaaa.inventoritoko.api.ApiClient
import com.rtamaaa.inventoritoko.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun getProducts() {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.getProducts()
                if (response.isSuccessful && response.body()?.success == true) {
                    _products.value = response.body()?.products ?: emptyList()
                    _errorMessage.value = ""
                } else {
                    _errorMessage.value = "Gagal memuat produk: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Terjadi error: ${e.localizedMessage}"
            }
        }
    }
}
