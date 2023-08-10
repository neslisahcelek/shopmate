package com.example.shoppingscanner.presentation.ui.productlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.component.ShopButtons
import com.example.shoppingscanner.component.ShopList
import com.example.shoppingscanner.component.ShopTexts
import com.example.shoppingscanner.domain.dto.ListProduct
import com.example.shoppingscanner.presentation.ui.Screen
import com.example.shoppingscanner.presentation.ui.base.BaseEvent
import com.example.shoppingscanner.presentation.ui.theme.PurplePrimary
import com.example.shoppingscanner.util.showToast


@Composable
fun ProductListScreen(
    navController: NavHostController,
    viewModel : ProductListViewModel
) {

    val state by rememberUpdatedState(newValue = viewModel.state.value)
    val context = LocalContext.current

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
                        top=10.dp,
                        start = 10.dp),
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

                ShopButtons.Small(
                    text = stringResource(R.string.buy_now),
                    onClick = {
                        navController.navigate(Screen.ContinueWithBarcodeScreen.route)
                    },
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 15.dp)
                )
            }

        }


    }
}

