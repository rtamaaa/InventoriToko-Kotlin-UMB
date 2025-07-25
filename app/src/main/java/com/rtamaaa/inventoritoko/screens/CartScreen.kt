package com.rtamaaa.inventoritoko.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rtamaaa.inventoritoko.viewmodel.CartViewModel
import com.rtamaaa.inventoritoko.ui.theme.BluePrimary

@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    onCheckout: () -> Unit // Gunakan ini untuk navigasi ke CheckoutScreen
) {
    val cartItems = cartViewModel.cartItems.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Keranjang Belanja",
            style = MaterialTheme.typography.headlineMedium,
            color = BluePrimary
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (cartItems.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Keranjang kosong.", style = MaterialTheme.typography.bodyLarge)
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(cartItems) { product ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = product.imageUrl
                                    ?: "https://cdn.pixabay.com/photo/2016/03/27/07/12/apple-1282241_1280.jpg",
                                contentDescription = product.name,
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .padding(end = 16.dp)
                            )

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(product.name, style = MaterialTheme.typography.titleMedium)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    "Rp ${product.price}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = BluePrimary
                                )
                                Text("Stok: ${product.stock}", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { cartViewModel.clearCart() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Kosongkan Keranjang")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { onCheckout() }, // Panggil callback yang diberikan!
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)
            ) {
                Text("Checkout")
            }
        }
    }
}
