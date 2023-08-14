package com.example.shoppingscanner.presentation.ui.navigation
import androidx.navigation.NavHostController

class NavActions (
    private val navController: NavHostController
){
    inner class DontWaitActions {
        val dontWaitToProductListAction : () -> Unit = {
            navController.navigate(Screen.ProductListScreen.route)
        }
    }

    inner class ProductListActions {
        val productListToBarcodeScannerAction : () -> Unit = {
            navController.navigate(Screen.BarcodeScannerScreen.route)
        }
    }

    inner class BarcodeScannerActions {
        val barcodeScannerToCartAction : () -> Unit = {
            navController.navigate(Screen.CartScreen.route)
        }
    }

    inner class CartActions {
        val cartToPaymentCompletedAction : () -> Unit = {
            navController.navigate(Screen.PaymentCompletedScreen.route)
        }
    }

    inner class PaymentCompletedActions {
        val PaymentCompletedToDontWaitAction : () -> Unit = {
            navController.navigate(Screen.DontWaitScreen.route)
        }
    }
}