package com.example.shoppingscanner.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.shoppingscanner.presentation.ui.Screen
import com.example.shoppingscanner.presentation.ui.barcode_scanner.BarcodeScannerScreen
import com.example.shoppingscanner.presentation.ui.CartScreen
import com.example.shoppingscanner.presentation.ui.ContinueWithBarcodeScreen
import com.example.shoppingscanner.presentation.ui.DontWaitScreen
import com.example.shoppingscanner.presentation.ui.PaymentCompletedScreen
import com.example.shoppingscanner.presentation.viewmodel.ProductViewModel

@Composable
fun setupNavGraph(navController: NavHostController, viewModel:ProductViewModel) {
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
            route = Screen.CartScreen.route + "/{barcode}",
            arguments = listOf(
                navArgument("barcode"){
                    type = NavType.StringType
                }
            )
        ) {
            CartScreen(
                viewModel = viewModel,
                navController = navController,
                barcode = it.arguments?.getString("barcode")!!
            )
        }

        composable(
            route = Screen.PaymentCompletedScreen.route + "/{barcode}",
            arguments = listOf(
                navArgument("barcode"){
                    type = NavType.StringType
                }
            )
        ) { entry ->
            PaymentCompletedScreen(
                viewModel = viewModel,
                navController = navController,
                barcode= entry.arguments?.getString("barcode")!!
            )
        }


    }
}

