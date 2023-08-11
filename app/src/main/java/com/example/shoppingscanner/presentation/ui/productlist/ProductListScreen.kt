package com.example.shoppingscanner.presentation.ui.productlist

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.component.ShopButtons
import com.example.shoppingscanner.component.ShopList
import com.example.shoppingscanner.domain.dto.ListProduct
import com.example.shoppingscanner.presentation.ui.Screen
import com.example.shoppingscanner.presentation.ui.base.BaseEvent
import com.example.shoppingscanner.presentation.ui.theme.PurpleGrey80
import com.example.shoppingscanner.presentation.ui.theme.PurplePrimary
import com.example.shoppingscanner.util.showToast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.example.shoppingscanner.component.ShopTexts


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    navController: NavHostController,
    viewModel : ProductListViewModel
) {

    val state by rememberUpdatedState(newValue = viewModel.state.value)
    val context = LocalContext.current

    val shoppingList = viewModel.shoppingListState.value.shoppingList
    var isShoppingListVisible by remember {
        mutableStateOf(false)
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
    LaunchedEffect(key1 = viewModel){
        viewModel.getProductListFromAPI(category = "Dekorasyon")
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ){
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ShopList.CategoryList(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        start = 10.dp
                    ),
                viewModel = viewModel
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally),

            ){
                viewModel.state.value.productList?.let {
                    ShopList.ProductList(
                        productList = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        onClick = { product ->
                            viewModel.addToList(product)
                        }
                    )
                }
                BottomAppBar (
                    modifier = Modifier
                        .height(55.dp)
                        .align(Alignment.BottomCenter)
                ){
                    ConstraintLayout (
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        val (icon, button) = createRefs()

                        ShopButtons.Small(
                            text = stringResource(R.string.continue_with_barcode),
                            onClick = {
                                navController.navigate(Screen.BarcodeScannerScreen.route)
                            },
                            modifier = Modifier
                                .constrainAs(button) {
                                    bottom.linkTo(parent.bottom)
                                    centerHorizontallyTo(parent)
                                }
                                .width(200.dp)
                        )

                        IconButton(
                            onClick = { isShoppingListVisible = !isShoppingListVisible },
                            modifier = Modifier
                                .constrainAs(icon) {
                                    end.linkTo(parent.end)
                                    start.linkTo(button.end)
                                }
                        ) {
                            Icon(
                                imageVector = Icons.Default.List,
                                tint = PurplePrimary,
                                contentDescription = null
                            )
                        }
                    }
                }
                if (isShoppingListVisible) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            isShoppingListVisible = false
                        }
                    )
                    {
                        BottomSheetContent(
                            list = shoppingList)
                    }
                }

            }

        }


    }
}

@Composable
fun BottomSheetContent(
    list : List<ListProduct>
) {
    ShopTexts.Title1Bold(
        text = stringResource(id = R.string.my_shopping_list),
        modifier = Modifier.fillMaxWidth()
            .padding(bottom=5.dp),
        textAlign = TextAlign.Center,
        color = PurplePrimary,
        textDecoration = TextDecoration.Underline
    )
    list.let {
        ShopList.BottomSheetProductList(
            productList = it,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom=20.dp)
        )
    }
}


