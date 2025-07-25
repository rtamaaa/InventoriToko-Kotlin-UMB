package com.rtamaaa.inventoritoko.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rtamaaa.inventoritoko.viewmodel.ProductViewModel
import com.rtamaaa.inventoritoko.viewmodel.CartViewModel
import com.rtamaaa.inventoritoko.model.Product

@Composable
fun MainScreen(
    navController: NavHostController,
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel
) {
    val products by productViewModel.products.collectAsState()
    val errorMessage by productViewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        productViewModel.getProducts()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Product List", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(products) { product ->
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(product.name, style = MaterialTheme.typography.titleMedium)
                        Text("Rp ${product.price}")
                        Text("Stock: ${product.stock}")
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(onClick = {
                                navController.navigate("productDetail/${product.id}")
                            }) {
                                Text("Lihat Detail")
                            }
                        }
                    }
                }
            }
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }
    }
}
