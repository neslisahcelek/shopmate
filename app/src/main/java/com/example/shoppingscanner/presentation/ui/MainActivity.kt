package com.example.shoppingscanner.presentation.ui
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shoppingscanner.presentation.ui.navigation.setupNavGraph
import com.example.shoppingscanner.presentation.ui.theme.ShoppingScannerTheme
import com.example.shoppingscanner.presentation.ui.barcode_scanner.ProductViewModel
import com.example.shoppingscanner.presentation.ui.navigation.NavActions
import com.example.shoppingscanner.presentation.ui.productlist.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController:NavHostController
    lateinit var navActions: NavActions
    val productViewModel : ProductViewModel by viewModels()
    val productListViewModel : ProductListViewModel by viewModels()
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

