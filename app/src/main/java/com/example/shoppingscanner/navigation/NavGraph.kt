package com.example.shoppingscanner.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.shoppingscanner.Screen
import com.example.shoppingscanner.model.Product
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
            ContinueWithBarcodeScreen(navController)
        }

        composable(
            route = Screen.BarcodeScannerScreen.route
        ) {
            BarcodeScannerScreen(navController)
        }

        composable(
            route = Screen.CartScreen.route + "?cartProduct={cartProduct}",
            arguments = listOf(
                navArgument("cartProduct"){
                    type = NavType.ReferenceType
                }
            )
        ) { entry ->
            CartScreen(
                navController,
                product=entry.arguments?.getParcelable("cartProduct",Product::class.java))
        }

        composable(
            route = Screen.PaymentCompletedScreen.route
        ) {
            PaymentCompletedScreen(navController)
        }

    }
}

