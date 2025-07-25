package com.rtamaaa.inventoritoko.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Main : Screen("main")
    object ProductDetail : Screen("productDetail/{productId}/{productName}/{productPrice}/{productStock}") {
        fun createRoute(id: Int, name: String, price: Double, stock: Int): String {
            return "productDetail/$id/$name/$price/$stock"
        }
    }
    object Cart : Screen("cart")
}
