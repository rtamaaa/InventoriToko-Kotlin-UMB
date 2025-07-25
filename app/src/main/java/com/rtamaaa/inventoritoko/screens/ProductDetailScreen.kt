package com.rtamaaa.inventoritoko.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rtamaaa.inventoritoko.model.Product
import com.rtamaaa.inventoritoko.ui.theme.BluePrimary

@Composable
fun ProductDetailScreen(
    product: Product,
    onAddToCart: (Product) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // === Gambar Produk ===
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            AsyncImage(
                model = product.imageUrl
                    ?: "https://cdn.pixabay.com/photo/2016/03/27/07/12/apple-1282241_1280.jpg",
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        // === Info Produk ===
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.headlineMedium
            )

            Text(
                text = "Harga: Rp ${product.price}",
                style = MaterialTheme.typography.titleMedium,
                color = BluePrimary
            )

            Text(
                text = "Stok: ${product.stock}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Divider(thickness = 1.dp)

        Spacer(modifier = Modifier.weight(1f))

        // === Tombol ===
        Button(
            onClick = { onAddToCart(product) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)
        ) {
            Text("Tambah ke Keranjang")
        }
    }
}
