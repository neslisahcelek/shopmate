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
import com.example.shoppingscanner.presentation.ui.productlist.ProductListViewModel

@Composable
fun setupNavGraph(
    navController: NavHostController,
    productViewModel: ProductViewModel,
    productListViewModel: ProductListViewModel
) {
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
            ProductListScreen(navController, productListViewModel)
        }

        composable(
            route = Screen.ContinueWithBarcodeScreen.route
        ) {
            ContinueWithBarcodeScreen(navController)
        }

        composable(
            route = Screen.BarcodeScannerScreen.route
        ) {
            BarcodeScannerScreen(navController, viewModel = productViewModel)
        }

        composable(
            route = Screen.CartScreen.route,

        ) {
            CartScreen(
                viewModel = productViewModel,
                navController = navController
            )
        }

        composable(
            route = Screen.PaymentCompletedScreen.route,
        ) { entry ->
            PaymentCompletedScreen(
                viewModel = productViewModel,
                navController = navController,
            )
        }


    }
}

