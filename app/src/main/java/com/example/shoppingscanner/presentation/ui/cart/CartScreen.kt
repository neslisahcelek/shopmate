package com.example.shoppingscanner.presentation.ui.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.domain.model.CartProduct
import com.example.shoppingscanner.presentation.ui.Screen
import com.example.shoppingscanner.presentation.ui.barcode_scanner.ProductViewModel
import com.example.shoppingscanner.util.ShopButtons
import com.example.shoppingscanner.util.ShopList
import com.example.shoppingscanner.util.ShopTexts

@Composable
fun CartScreen(
    navController: NavController,
    viewModel : ProductViewModel
) {

    val state = viewModel.state.value

    var productList = remember {
        mutableStateListOf<CartProduct>()
    }

    state.product?.let { productList.add(it) }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        ShopTexts.LargeTitle(
            text = stringResource(id = R.string.my_cart),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            modifier = Modifier.padding(top=5.dp)
        )

        Box(modifier = Modifier.fillMaxHeight(0.8f)){
            state.product?.let {
                ShopList.ProductRow(
                    product= it,
                )
            }
        }

        ShopButtons.Primary(
            onClick = {
                navController.navigate(Screen.PaymentCompletedScreen.route)
            },
            text = stringResource(R.string.pay_with_hadi),
        )

        Spacer(modifier = Modifier.padding(bottom=8.dp))

    }

}





