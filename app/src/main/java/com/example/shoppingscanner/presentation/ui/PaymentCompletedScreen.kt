package com.example.shoppingscanner.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.util.ShopButtons
import com.example.shoppingscanner.util.ShopList
import com.example.shoppingscanner.util.ShopTexts
import com.example.shoppingscanner.domain.model.CartProduct
import com.example.shoppingscanner.presentation.viewmodel.ProductViewModel


@Composable
fun PaymentCompletedScreen(
    barcode: String,
    navController: NavController,
    viewModel : ProductViewModel
) {

    var productList = remember {
        mutableStateListOf<CartProduct>()
    }
    productList.add(CartProduct("123","Süt","15.0","",1))
    productList.add(CartProduct("123","Süt","15.0","",1))
    productList.add(CartProduct("123","Süt","15.0","",1))
    productList.add(CartProduct("123","Süt","15.0","",1))
    productList.add(CartProduct("123","Süt","15.0","",1))
    productList.add(CartProduct("123","Süt","15.0","",1))
    productList.add(CartProduct("123","Süt","15.0","",1))
    productList.add(CartProduct("123","Süt","15.0","",1))
    productList.add(CartProduct("123","Süt","15.0","",1))
    productList.add(CartProduct("123","Süt","15.0","",1))
    productList.add(CartProduct("123","Süt","15.0","",1))
    productList.add(CartProduct("123","Süt","15.0","",1))

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
) {
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.barcode),
            contentDescription = "barcode",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
            )
        ShopTexts.Title1Bold(
            text = stringResource(R.string.payment_successfull),
            textAlign = TextAlign.Center,
            color = Color.Green,
            modifier = Modifier.fillMaxWidth()
        )
        ShopList.ProductList(
            productList = productList,
            modifier = Modifier.height(380.dp)
            )
        ShopButtons.Primary(
            onClick = {
                navController.navigate(Screen.BarcodeScannerScreen.route)
            },
            text = stringResource(R.string.home_screen),
        )
    }
}
/*
@Composable
@Preview(showBackground = true)
fun PaymentCompletedScreenPreview() {
    var product = CartProduct("123","Süt","15.0","",1)
    PaymentCompletedScreen(product.barcode_number,navController = rememberNavController())
}

 */