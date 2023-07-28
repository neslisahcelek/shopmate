package com.example.shoppingscanner.presentation.ui.barcode_scanner

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.presentation.ui.Screen
import com.example.shoppingscanner.presentation.viewmodel.ProductViewModel
import com.example.shoppingscanner.util.ShopButtons
import com.example.shoppingscanner.util.ShopTexts


@Composable
fun BarcodeScannerScreen(
    navController: NavController,
    viewModel : ProductViewModel
    ){

    val state = viewModel.state.value

    val barcodeNumber = "3614272049529"


    Column(
        modifier= Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier= Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.barcode),
                contentDescription = "barcode",
                contentScale = ContentScale.Fit,


            )
        }
        Column(
            modifier= Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp, top = 15.dp, bottom = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Row {
                    ShopTexts.BodyBold(
                        text = "Ürün: ",
                        modifier = Modifier.padding(end = 5.dp),
                        textAlign = TextAlign.Start
                    )
                    ShopTexts.BodyRegular(
                        text = "${state.product!!.title}",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    ShopTexts.BodyBold(
                        text = "Fiyat: ",
                        modifier = Modifier.padding(end = 5.dp),
                        textAlign = TextAlign.Start
                    )
                    ShopTexts.BodyRegular(
                        text = "${state.product!!.price}" +" ₺",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start
                    )
                }
            }

            Column {
                ShopTexts.BodyBold(
                    text = stringResource(R.string.price_sum) + "${state.product?.price.toString()}",
                    fontSize = 12.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(5.dp, end = 30.dp)
                        .fillMaxWidth(0.9f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, bottom = 15.dp, top = 5.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {

                    ShopButtons.Small(
                        text = stringResource(R.string.add_to_cart),
                        onClick = {
                            viewModel.onEvent(BarcodeScannerEvent.GetData(barcodeNumber))
                        },
                    )

                    ShopButtons.Small(
                        text = stringResource(R.string.buy_now),
                        onClick = {
                            navController.navigate(Screen.CartScreen.route + "/12344")
                        },
                    )

                }

            }

        }
    }

}

fun addToCart() {

}

