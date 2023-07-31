package com.example.shoppingscanner.presentation.ui.barcode_scanner

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.presentation.ui.Screen
import com.example.shoppingscanner.presentation.ui.theme.PurplePrimary
import com.example.shoppingscanner.util.ShopButtons
import com.example.shoppingscanner.util.ShopTexts


@Composable
fun BarcodeScannerScreen(
    navController: NavController,
    viewModel : ProductViewModel
    ) {

    val state = viewModel.state.value
    val barcodeNumber = "3614272049529"

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember {
        LifecycleCameraController(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AndroidView(
                factory = { context ->
                    PreviewView(context).apply {
                        layoutParams = LinearLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT)
                        scaleType = PreviewView.ScaleType.FILL_CENTER
                    }.also {previewView ->
                        previewView.controller = cameraController
                        cameraController.bindToLifecycle(lifecycleOwner)
                    }
                },
                modifier = Modifier.height(200.dp)
            )
        }
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 5.dp, bottom = 15.dp),

                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.End)
                        .size(40.dp)
                        .padding(start = 8.dp),
                    tint = PurplePrimary
                )
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
                            text = "${state.product!!.price}" + " ₺",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ){
            ShopTexts.BodyBold(
                text = stringResource(R.string.price_sum) + "${state.product?.price.toString()} ₺",
                fontSize = 12.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(5.dp, end = 40.dp)
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
                        viewModel.getBarcode()
                        //viewModel.getDataFromAPI(barcodeNumber) //code
                    },
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





