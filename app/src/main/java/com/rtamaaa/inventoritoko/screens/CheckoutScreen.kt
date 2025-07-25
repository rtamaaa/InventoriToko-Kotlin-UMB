package com.rtamaaa.inventoritoko.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.rtamaaa.inventoritoko.viewmodel.CartViewModel
import com.rtamaaa.inventoritoko.ui.theme.BluePrimary

@Composable
fun CheckoutScreen(
    navController: NavHostController,
    cartViewModel: CartViewModel
) {
    val cartItems = cartViewModel.cartItems.collectAsState().value
    val totalPrice = cartItems.sumOf { it.price }

    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Checkout",
                style = MaterialTheme.typography.headlineMedium,
                color = BluePrimary
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (cartItems.isEmpty()) {
                Text("Keranjang kosong. Tidak ada item untuk checkout.")
            } else {
                cartItems.forEach { product ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        AsyncImage(
                            model = product.imageUrl
                                ?: "https://cdn.pixabay.com/photo/2016/03/27/07/12/apple-1282241_1280.jpg",
                            contentDescription = product.name,
                            modifier = Modifier
                                .size(60.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(product.name, style = MaterialTheme.typography.bodyLarge)
                            Text("Rp ${product.price}", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Divider()

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Total: Rp $totalPrice",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Button(
            onClick = {
                if (cartItems.isNotEmpty()) {
                    cartViewModel.clearCart()
                    showDialog = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            shape = RoundedCornerShape(12.dp),
            enabled = cartItems.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)
        ) {
            Text("Bayar Sekarang")
        }
    }

    // === Popup ===
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        navController.popBackStack() // balik ke screen sebelumnya
                    }
                ) {
                    Text("OK")
                }
            },
            title = {
                Text("Pembayaran Berhasil")
            },
            text = {
                Text("Terima kasih! Pembayaran Anda berhasil diproses.")
            },
            shape = RoundedCornerShape(12.dp)
        )
    }
}
