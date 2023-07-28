package com.example.shoppingscanner.presentation.ui
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shoppingscanner.presentation.ui.navigation.setupNavGraph
import com.example.shoppingscanner.presentation.ui.theme.ShoppingScannerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var navController:NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingScannerTheme {
                navController = rememberNavController()
                setupNavGraph(navController)

            }
        }
    }
}
