package com.davidvarela.bizorder.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.davidvarela.bizorder.presentation.navigation.BottomNavigationBar
import com.davidvarela.bizorder.presentation.navigation.Screen

@Composable
fun MainScreen(
    navHostController: NavHostController
) {

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navHostController) }
    ) { innerPadding ->

        NavHost(
            navController = navHostController,
            startDestination = Screen.Home.route,
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    modifier = Modifier.padding(innerPadding)
                )
            }

            composable(Screen.Create.route) {

            }

            composable(Screen.PreOrders.route) {

            }
        }
    }
}