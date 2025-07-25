package com.rtamaaa.inventoritoko

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.rtamaaa.inventoritoko.ui.navigation.NavGraph
import com.rtamaaa.inventoritoko.viewmodel.AuthViewModel
import com.rtamaaa.inventoritoko.viewmodel.CartViewModel
import com.rtamaaa.inventoritoko.viewmodel.ProductViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val authViewModel = AuthViewModel()
            val productViewModel = ProductViewModel()
            val cartViewModel = CartViewModel()

            NavGraph(
                navController = navController,
                authViewModel = authViewModel,
                productViewModel = productViewModel,
                cartViewModel = cartViewModel
            )
        }
    }
}
