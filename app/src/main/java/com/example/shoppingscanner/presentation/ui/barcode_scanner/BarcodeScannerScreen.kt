package com.example.shoppingscanner.presentation.ui.barcode_scanner

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.shoppingscanner.R
import com.example.shoppingscanner.component.ShopButtons
import com.example.shoppingscanner.component.ShopDialogs
import com.example.shoppingscanner.component.ShopList
import com.example.shoppingscanner.component.ShopTexts
import com.example.shoppingscanner.domain.dto.ListProduct
import com.example.shoppingscanner.presentation.ui.base.BaseEvent
import com.example.shoppingscanner.presentation.ui.navigation.NavActions
import com.example.shoppingscanner.presentation.ui.shared.ShoppingListState
import com.example.shoppingscanner.presentation.ui.theme.Purple80
import com.example.shoppingscanner.presentation.ui.theme.PurpleGrey40
import com.example.shoppingscanner.presentation.ui.theme.PurplePrimary
import com.example.shoppingscanner.util.showShortToast

@Composable
fun BarcodeScannerScreen(
    action: NavActions.BarcodeScannerActions,
    viewModel : ProductViewModel
    ) {
    val state by viewModel.state.collectAsState()

    val shoppingListState by rememberUpdatedState(newValue = viewModel.shoppingListState.value)

    val context = LocalContext.current

    var isDialogOpen by remember {
        mutableStateOf(false)
    }

    state.messageId?.let {
        showShortToast(
            context = context,
            message = stringResource(id = it)
        )

        viewModel.onEvent(
            BaseEvent.OnHandledMessage(
                stringResource(id = it)
            )
        )
    }

    val baseEvent by viewModel.baseEvent.collectAsState(null)
    when (baseEvent) {
        is BaseEvent.RemoveProduct -> {
            viewModel.onHandledEvent()
        }

        else -> {}
    }

    var isShoppingListVisible by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LaunchedEffect(key1 = viewModel){
                viewModel.onEvent(BaseEvent.GetProduct())
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)

            ){
                AsyncImage(
                    model = state.product?.image,
                    contentDescription = "Product image",
                    placeholder = painterResource(id = R.drawable.baseline_image_24),
                    contentScale = FixedScale(0.9f),
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .fillMaxWidth()
                        .padding(vertical = 50.dp, horizontal = 20.dp)
                )
                IconButton(
                    onClick = { viewModel.onEvent(BaseEvent.GetProduct()) },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 50.dp)
                        .border(3.dp, PurpleGrey40, CircleShape)

                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_camera_alt_24),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(5.dp),
                        tint = PurplePrimary,
                        contentDescription = null
                    )
                }
            }

        }
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.4f)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        color = Purple80, shape = RoundedCornerShape(
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
                        CartBadgedBox(
                            quantity = "${state.product?.quantity}",
                            modifier = Modifier
                                .align(Alignment.Center)
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
                                    viewModel.checkShoppingList()
                                    isDialogOpen = true
                                },
                                modifier = Modifier.constrainAs(buyNowButton) {
                                    bottom.linkTo(parent.bottom)
                                    end.linkTo(parent.end)
                                }
                            )
                    if (isDialogOpen) {
                        if (state.cartProducts.isNullOrEmpty()){
                            showShortToast(context, stringResource(id = R.string.cart_is_empty))
                            isDialogOpen = false
                        }
                        else if (state.missingProducts.isNullOrEmpty()) {
                            isDialogOpen = false
                            action.barcodeScannerToCartAction.invoke()
                        }else{
                            println("alert")
                            ShopDialogs.ShopDialog(
                                modifier = Modifier,
                                onDismissRequest = {
                                    isDialogOpen = false },
                                title = { Text(text = stringResource(R.string.barcode_scanner_dialog_title)) },
                                text = { Text(text = stringResource(R.string.barcode_scanner_dialog_message),) },
                                confirmButton = {
                                    ShopButtons.Small(
                                        text = stringResource(R.string.dialog_confirm_button),
                                        onClick = {
                                           action.barcodeScannerToCartAction.invoke()
                                        },
                                        modifier = Modifier
                                            .width(100.dp)
                                    )
                                },
                                dismissButton = {
                                    ShopButtons.Small(
                                        text = stringResource(R.string.dialog_dismiss_button),
                                        onClick = {isDialogOpen = false},
                                        modifier = Modifier
                                            .width(100.dp)
                                    )
                                },
                            )
                        }
                    }
                }

            }
        IconButton(
            onClick = { isShoppingListVisible = !isShoppingListVisible },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                imageVector = Icons.Default.List,
                tint = PurplePrimary,
                contentDescription = null
            )
        }
        if (isShoppingListVisible) {
            shoppingListState.shoppingList?.let {
                ShoppingListDrawer(
                    shoppingList = it,
                    viewModel = viewModel
                    )
            }
        }
        }
    }

@Composable
fun ShoppingListDrawer(
    shoppingList : List<ListProduct>,
    viewModel : ProductViewModel
){
    ModalDrawerSheet (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.6f)
            .padding(horizontal = 40.dp, vertical = 15.dp)
    ){
        ShopTexts.Title1Bold(
            text = stringResource(id = R.string.my_shopping_list),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, bottom = 5.dp),
            textAlign = TextAlign.Center,
            color = PurplePrimary,
            textDecoration = TextDecoration.Underline
        )
        shoppingList.let {
            ShopList.ShoppingProductList(
                productList = it,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                viewModel = viewModel
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartBadgedBox(quantity: String?, modifier: Modifier){
    BadgedBox(
        modifier = modifier,
        badge = {
            Badge(
                contentColor = PurplePrimary,
                containerColor = Color.White
            ){ ShopTexts.BodyBold(
                text = quantity ?: "0",
                fontSize = 16.sp
            )
            }
        }
    ) {
        Icon(
            Icons.Default.ShoppingCart,
            contentDescription = "Shopping Cart",
            tint = PurplePrimary
        )
    }
}





