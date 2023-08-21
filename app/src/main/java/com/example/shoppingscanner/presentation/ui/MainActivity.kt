package com.example.shoppingscanner.presentation.ui
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shoppingscanner.presentation.ui.navigation.setupNavGraph
import com.example.shoppingscanner.presentation.ui.theme.ShoppingScannerTheme
import com.example.shoppingscanner.presentation.ui.barcode_scanner.ProductVM
import com.example.shoppingscanner.presentation.ui.navigation.NavActions
import com.example.shoppingscanner.presentation.ui.productlist.ProductListVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController:NavHostController
    lateinit var navActions: NavActions
    val productViewModel : ProductVM by viewModels()
    val productListViewModel : ProductListVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingScannerTheme {
                navController = rememberNavController()
                navActions = NavActions(navController)
                setupNavGraph(
                    navController = navController,
                    navActions = navActions,
                    productViewModel = productViewModel,
                    productListViewModel = productListViewModel
                )
            }
        }
    }
}

