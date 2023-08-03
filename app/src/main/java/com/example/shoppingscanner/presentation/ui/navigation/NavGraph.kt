package com.example.shoppingscanner.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppingscanner.presentation.ui.Screen
import com.example.shoppingscanner.presentation.ui.barcode_scanner.BarcodeScannerScreen
import com.example.shoppingscanner.presentation.ui.cart.CartScreen
import com.example.shoppingscanner.presentation.ui.continue_with_barcode.ContinueWithBarcodeScreen
import com.example.shoppingscanner.presentation.ui.dont_wait.DontWaitScreen
import com.example.shoppingscanner.presentation.ui.payment.PaymentCompletedScreen
import com.example.shoppingscanner.presentation.ui.barcode_scanner.ProductViewModel
import com.example.shoppingscanner.presentation.ui.productlist.ProductListScreen

@Composable
fun setupNavGraph(navController: NavHostController, viewModel: ProductViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.DontWaitScreen.route)
    {
        composable(
            route = Screen.DontWaitScreen.route
        ) {
            DontWaitScreen(navController)
        }
        composable(
            route = Screen.ProductListScreen.route
        ) {
            ProductListScreen(navController)
        }

        composable(
            route = Screen.ContinueWithBarcodeScreen.route
        ) {
            ContinueWithBarcodeScreen(navController)
        }

        composable(
            route = Screen.BarcodeScannerScreen.route
        ) {
            BarcodeScannerScreen(navController, viewModel = viewModel)
        }

        composable(
            route = Screen.CartScreen.route,

        ) {
            CartScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable(
            route = Screen.PaymentCompletedScreen.route,
        ) { entry ->
            PaymentCompletedScreen(
                viewModel = viewModel,
                navController = navController,
            )
        }


    }
}

