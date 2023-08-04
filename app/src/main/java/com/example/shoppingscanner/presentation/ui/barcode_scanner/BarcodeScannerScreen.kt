package com.example.shoppingscanner.presentation.ui.barcode_scanner

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.presentation.ui.Screen
import com.example.shoppingscanner.presentation.ui.theme.PurplePrimary
import com.example.shoppingscanner.component.ShopButtons
import com.example.shoppingscanner.component.ShopTexts
import com.example.shoppingscanner.presentation.ui.base.BaseEvent
import com.example.shoppingscanner.util.showToast


@Composable
fun BarcodeScannerScreen(
    navController: NavController,
    viewModel : ProductViewModel
    ) {
    val state by rememberUpdatedState(newValue = viewModel.state.value)

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember {
        LifecycleCameraController(context)
    }

    state.messageId?.let {
        showToast(
            context = context,
            message = stringResource(id = it)
        )

        viewModel.onEvent(
            BaseEvent.OnHandledMessage(
                stringResource(id = it)
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            LaunchedEffect(key1 = viewModel){
                viewModel.onEvent(BaseEvent.GetData())
            }

            Box {
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
                    modifier = Modifier.fillMaxHeight()
                ){ previewView ->


                }

            }


        }
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        color = Color.White, shape = RoundedCornerShape(
                            topEnd = 50.dp, topStart = 50.dp
                        )
                    )
                    .padding(start = 35.dp, end = 35.dp, top = 10.dp, bottom = 15.dp),

                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier.size(50.dp),
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.TopCenter),
                            tint = PurplePrimary
                        )
                        ShopTexts.BodyBold(
                            text = "${state.product?.quantity}",
                            fontSize = 14.sp,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 8.dp)
                                ,
                            color = Color.White
                        )
                    }

                }


                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Row {
                        ShopTexts.BodyBold(
                            text = stringResource(id = R.string.product),
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
                            text = stringResource(id = R.string.price),
                            modifier = Modifier.padding(end = 5.dp),
                            textAlign = TextAlign.Start
                        )
                        ShopTexts.BodyRegular(
                            text = "${state.product!!.price} ₺",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Start
                        )
                    }
                }
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .padding(bottom = 15.dp),
                ) {
                    val (totalText, addToCartButton, buyNowButton) = createRefs()

                    ShopTexts.BodyBold(
                        text = stringResource(R.string.price_sum,state.totalPrice),
                        fontSize = 12.sp,
                        modifier = Modifier.constrainAs(totalText) {
                            bottom.linkTo(buyNowButton.top, margin = 2.dp)
                            centerHorizontallyTo(buyNowButton)
                        }
                    )
                            ShopButtons.Small(
                                text = stringResource(R.string.add_to_cart),
                                onClick = {
                                    viewModel.addToCart()
                                },
                                modifier = Modifier.constrainAs(addToCartButton) {
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                },
                            )

                            ShopButtons.Small(
                                text = stringResource(R.string.buy_now),
                                onClick = {
                                    navController.navigate(Screen.CartScreen.route)
                                },
                                modifier = Modifier.constrainAs(buyNowButton) {
                                    bottom.linkTo(parent.bottom)
                                    end.linkTo(parent.end)
                                }
                            )
                }

            }


        }

    }






