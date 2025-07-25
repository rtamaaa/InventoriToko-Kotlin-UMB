package com.rtamaaa.inventoritoko.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rtamaaa.inventoritoko.model.Product

@Composable
fun ProductDetailScreen(
    product: Product,
    onAddToCart: (Product) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(product.name, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Harga: Rp ${product.price}")
        Text("Stok: ${product.stock}")
        Spacer(modifier = Modifier.height(24.dp))

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onAddToCart(product) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tambah ke Keranjang")
        }
    }
}
