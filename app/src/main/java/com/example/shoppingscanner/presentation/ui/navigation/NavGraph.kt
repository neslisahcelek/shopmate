package com.example.shoppingscanner.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shoppingscanner.presentation.ui.barcode_scanner.BarcodeScannerScreen
import com.example.shoppingscanner.presentation.ui.cart.CartScreen
import com.example.shoppingscanner.presentation.ui.dont_wait.DontWaitScreen
import com.example.shoppingscanner.presentation.ui.payment.PaymentCompletedScreen
import com.example.shoppingscanner.presentation.ui.barcode_scanner.ProductVM
import com.example.shoppingscanner.presentation.ui.productlist.ProductListScreen
import com.example.shoppingscanner.presentation.ui.productlist.ProductListVM

@Composable
fun setupNavGraph(
    navController: NavHostController,
    navActions: NavActions,
    productViewModel: ProductVM,
    productListViewModel: ProductListVM
) {
    NavHost(
        navController = navController,
        startDestination = Screen.DontWaitScreen.route)
    {
        composable(
            route = Screen.DontWaitScreen.route
        ) {
            DontWaitScreen(
                action = navActions.DontWaitActions()
            )
        }
        composable(
            route = Screen.ProductListScreen.route
        ) {
            ProductListScreen(
                action = navActions.ProductListActions(),
                viewModel = productListViewModel)
        }

        composable(
            route = Screen.BarcodeScannerScreen.route
        ) {
            BarcodeScannerScreen(
                action = navActions.BarcodeScannerActions(),
                viewModel = productViewModel
            )
        }

        composable(
            route = Screen.CartScreen.route,

        ) {
            CartScreen(
                action = navActions.CartActions(),
                viewModel = productViewModel
            )
        }

        composable(
            route = Screen.PaymentCompletedScreen.route,
        ) { entry ->
            PaymentCompletedScreen(
                action = navActions.PaymentCompletedActions(),
                viewModel = productViewModel
            )
        }


    }
}

