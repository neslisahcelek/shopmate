package com.example.shoppingscanner.presentation.ui.payment

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
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.util.ShopButtons
import com.example.shoppingscanner.util.ShopList
import com.example.shoppingscanner.util.ShopTexts
import com.example.shoppingscanner.domain.model.CartProduct
import com.example.shoppingscanner.presentation.ui.Screen
import com.example.shoppingscanner.presentation.ui.barcode_scanner.ProductViewModel
import com.example.shoppingscanner.presentation.ui.theme.Green


@Composable
fun PaymentCompletedScreen(
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
        Box(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)


        ){
            Image(
                painter = painterResource(id = R.drawable.purpledot),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
            )
            Image(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "tick",
                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.Center)
                    ,
                colorFilter = ColorFilter.tint(Green)

            )
        }

        ShopTexts.Title1Bold(
            text = stringResource(R.string.payment_successfull),
            textAlign = TextAlign.Center,
            color = Green,
            modifier = Modifier.fillMaxWidth()
        )
        Box(modifier = Modifier.fillMaxHeight(0.7f)){
            ShopList.ProductList(
                productList = state.cartProducts,
                modifier = Modifier
            )
        }

        ShopButtons.Primary(
            onClick = {
                navController.navigate(Screen.BarcodeScannerScreen.route)
            },
            text = stringResource(R.string.home_screen),
        )

        Spacer(modifier = Modifier.padding(bottom=8.dp))
    }
}
