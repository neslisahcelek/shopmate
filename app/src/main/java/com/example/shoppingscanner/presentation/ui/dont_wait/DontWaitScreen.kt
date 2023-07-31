package com.example.shoppingscanner.presentation.ui.dont_wait

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.presentation.ui.Screen
import com.example.shoppingscanner.util.ShopButtons
import com.example.shoppingscanner.presentation.ui.theme.PurplePrimary


@Composable
fun DontWaitScreen(
    navController: NavController
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        ShopButtons.Primary(
            text = stringResource(R.string.dont_wait),
            onClick = {
                navController.navigate(Screen.ContinueWithBarcodeScreen.route) },
        )

    }
}

@Composable
@Preview(showBackground = true)
fun DontWaitScreenPreview() {
    DontWaitScreen(navController = rememberNavController())
}