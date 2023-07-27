package com.example.shoppingscanner.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.Screen
import com.example.shoppingscanner.compose.ShopButtons
import com.example.shoppingscanner.compose.ShopTexts
import com.example.shoppingscanner.model.Product

@Composable
fun CartScreen(
    navController: NavController,
    product: Product?
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround
    ){
        ShopTexts.LargeTitle(
            text = stringResource(R.string.my_cart),
            textAlign = TextAlign.Center
        )
        ShopButtons.Primary(
            text = stringResource(R.string.pay_with_hadi),
            onClick = {
                navController.navigate(Screen.PaymentCompletedScreen.route) },
        )
    }


}

/*
@Composable
@Preview(showBackground = true)
fun CartScreenPreview() {
    CartScreen(navController = rememberNavController(product))
}

 */