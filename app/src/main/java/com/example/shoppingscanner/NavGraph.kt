package com.example.shoppingscanner

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppingscanner.ui.BarcodeScannerScreen
import com.example.shoppingscanner.ui.CartScreen
import com.example.shoppingscanner.ui.ContinueWithBarcodeScreen
import com.example.shoppingscanner.ui.DontWaitScreen
import com.example.shoppingscanner.ui.PaymentCompletedScreen

@Composable
fun setupNavGraph(navController: NavHostController) {
    NavHost(navController = navController,
        startDestination = Screen.DontWaitScreen.route)
    {
        composable(
            route = Screen.DontWaitScreen.route
        ) {
            DontWaitScreen(navController)
        }

        composable(
            route = Screen.ContinueWithBarcodeScreen.route
        ) {
            ContinueWithBarcodeScreen()
        }

        composable(
            route = Screen.BarcodeScannerScreen.route
        ) {
            BarcodeScannerScreen()
        }

        composable(
            route = Screen.CartScreen.route
        ) {
            CartScreen()
        }

        composable(
            route = Screen.PaymentCompletedScreen.route
        ) {
            PaymentCompletedScreen()
        }

    }
}

