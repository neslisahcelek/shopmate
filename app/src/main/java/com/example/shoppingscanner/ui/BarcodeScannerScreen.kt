package com.example.shoppingscanner.ui

import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.Start
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.Screen
import com.example.shoppingscanner.compose.ShopButtons
import com.example.shoppingscanner.compose.ShopTexts
import kotlinx.coroutines.withContext

@Composable
fun BarcodeScannerScreen(
    navController: NavController
    ){
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
                .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                ShopTexts.BodyRegular(
                    text = stringResource(R.string.continue_info),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(10.dp))

                ShopTexts.FootnoteMedium(
                    text = stringResource(R.string.continue_info),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
            }

            Column {
                ShopTexts.BodyBold(
                    text = stringResource(R.string.price_sum),
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
                            addToCart()},
                    )

                    ShopButtons.Small(
                        text = stringResource(R.string.buy_now),
                        onClick = {
                            navController.navigate(Screen.CartScreen.route)
                        },
                    )

                }

            }

        }
    }

}

fun addToCart() {
    TODO("Not yet implemented")
}


@Composable
@Preview(showBackground = true)
fun BarcodeScannerScreenPreview() {
    BarcodeScannerScreen(navController = rememberNavController())
}