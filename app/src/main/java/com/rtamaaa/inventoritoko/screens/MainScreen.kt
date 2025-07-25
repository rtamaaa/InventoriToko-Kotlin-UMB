package com.rtamaaa.inventoritoko.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.rtamaaa.inventoritoko.viewmodel.ProductViewModel
import com.rtamaaa.inventoritoko.viewmodel.CartViewModel
import com.rtamaaa.inventoritoko.ui.theme.BluePrimary

@Composable
fun MainScreen(
    navController: NavHostController,
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel
) {
    val products by productViewModel.products.collectAsState()
    val errorMessage by productViewModel.errorMessage.collectAsState()
    val cartItems by cartViewModel.cartItems.collectAsState()

    LaunchedEffect(Unit) {
        productViewModel.getProducts()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        // === App Bar & Keranjang ===
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Inventori Toko",
                style = MaterialTheme.typography.headlineMedium,
                color = BluePrimary
            )

            Button(
                onClick = {
                    navController.navigate("cart")
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)
            ) {
                Text("Keranjang (${cartItems.size})")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // === Produk ===
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products) { product ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
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
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                product.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "Rp ${product.price}",
                                style = MaterialTheme.typography.bodyLarge,
                                color = BluePrimary
                            )
                            Text(
                                "Stock: ${product.stock}",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(
                                    onClick = {
                                        navController.navigate("productDetail/${product.id}")
                                    },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(8.dp),
                                            colors = ButtonDefaults.buttonColors(containerColor = BluePrimary)
                                ) {
                                    Text("Detail")
                                }

                                OutlinedButton(
                                    onClick = {
                                        cartViewModel.addToCart(product)
                                    },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = BluePrimary)
                                ) {
                                    Text("Keranjang")
                                }
                            }
                        }
                    }
                }
            }
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
