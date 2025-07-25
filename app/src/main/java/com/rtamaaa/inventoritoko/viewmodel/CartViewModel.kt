package com.rtamaaa.inventoritoko.viewmodel

import androidx.lifecycle.ViewModel
import com.rtamaaa.inventoritoko.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CartViewModel : ViewModel() {
    private val _cartItems = MutableStateFlow<List<Product>>(emptyList())
    val cartItems: StateFlow<List<Product>> = _cartItems

    fun addToCart(product: Product) {
        _cartItems.value = _cartItems.value + product
    }
    fun clearCart() {
        _cartItems.value = emptyList()
    }
    fun onCheckout() {

    }
}
