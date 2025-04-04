package com.davidvarela.bizorder.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Create : Screen("create", "Crear", Icons.Default.Add)
    data object Home : Screen("home", "Órdenes", Icons.Default.Home)
    data object PreOrders : Screen("list", "Pre-órdenes", Icons.AutoMirrored.Filled.List)
    data object DetailOrder: Screen("detail/{order_id}", "Detalle", Icons.Default.Home) {
        fun getDetailRoute(orderId: String) = "detail/$orderId"
        const val ARG_ORDER_ID = "order_id"
    }
}