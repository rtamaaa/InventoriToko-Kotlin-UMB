package com.rtamaaa.inventoritoko.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rtamaaa.inventoritoko.model.Product
import com.rtamaaa.inventoritoko.ui.screens.*
import com.rtamaaa.inventoritoko.viewmodel.AuthViewModel
import com.rtamaaa.inventoritoko.viewmodel.CartViewModel
import com.rtamaaa.inventoritoko.viewmodel.ProductViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    productViewModel: ProductViewModel,
    cartViewModel: CartViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                navController = navController,
                viewModel = authViewModel,
                onResult = { success ->
                    if (success) {
                        navController.navigate(Screen.Main.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    }
                }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }
        composable(Screen.Main.route) {
            MainScreen(
                navController = navController,
                productViewModel = productViewModel,
                cartViewModel = cartViewModel
            )
        }
        composable("productDetail/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull()
            val product = productViewModel.products.value.find { it.id == productId }
            if (product != null) {
                ProductDetailScreen(
                    product = product,
                    onAddToCart = { cartViewModel.addToCart(it) }
                )
            }
        }
    }
}
