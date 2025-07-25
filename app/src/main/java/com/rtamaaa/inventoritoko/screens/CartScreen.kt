package com.rtamaaa.inventoritoko.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rtamaaa.inventoritoko.viewmodel.CartViewModel

@Composable
fun CartScreen(cartViewModel: CartViewModel) {
    val cartItems = cartViewModel.cartItems.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Keranjang Belanja", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        if (cartItems.isEmpty()) {
            Text("Keranjang kosong.")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(cartItems) { product ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(product.name, style = MaterialTheme.typography.titleMedium)
                            Text("Rp ${product.price}")
                            Text("Stok: ${product.stock}")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { cartViewModel.clearCart() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kosongkan Keranjang")
            }
        }
    }
}
