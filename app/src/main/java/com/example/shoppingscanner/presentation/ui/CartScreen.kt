package com.example.shoppingscanner.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.domain.model.CartProduct
import com.example.shoppingscanner.presentation.viewmodel.ProductViewModel
import com.example.shoppingscanner.util.ShopButtons
import com.example.shoppingscanner.util.ShopTexts

@Composable
fun CartScreen(
    navController: NavController,
    barcode: String,
    viewModel : ProductViewModel
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
                navController.navigate(Screen.PaymentCompletedScreen.route+ "/12344") },
        )
    }


}
/*

@Composable
@Preview(showBackground = true)
fun CartScreenPreview() {
    var product = CartProduct("1234","Süt","15.0","",1)
    CartScreen(navController = rememberNavController(),product.barcode_number, viewModel = vi)
}


 */
