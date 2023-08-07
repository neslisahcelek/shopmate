package com.example.shoppingscanner.presentation.ui.shoppinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingscanner.R
import com.example.shoppingscanner.component.ShopList
import com.example.shoppingscanner.component.ShopTexts
import com.example.shoppingscanner.presentation.ui.base.BaseEvent
import com.example.shoppingscanner.presentation.ui.productlist.ProductListViewModel
import com.example.shoppingscanner.presentation.ui.theme.PurplePrimary
import com.example.shoppingscanner.util.showToast

@Composable
fun ShoppingListScreen(
    viewModel : ProductListViewModel
){
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

    Surface(
        modifier = Modifier.fillMaxSize()
    ){
        Column {
            ShopTexts.LargeTitle(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                textAlign = TextAlign.Center,
                fontSize = 28.sp,
                text = stringResource(id = R.string.my_shopping_list)
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(PurplePrimary))

            viewModel.state.value.shoppingList?.let {
                ShopList.ShoppingProductList(
                    productList = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),

                )
            }
        }
    }



}