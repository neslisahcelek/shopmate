package com.example.shoppingscanner.presentation.ui.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingscanner.R
import com.example.shoppingscanner.domain.dto.CartProduct
import com.example.shoppingscanner.presentation.ui.barcode_scanner.ProductVM
import com.example.shoppingscanner.component.ShopButtons
import com.example.shoppingscanner.component.ShopList
import com.example.shoppingscanner.component.ShopTexts
import com.example.shoppingscanner.presentation.ui.navigation.NavActions

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CartScreen(
    action: NavActions.CartActions,
    viewModel : ProductVM
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
        ShopTexts.LargeTitle(
            text = stringResource(id = R.string.my_cart),
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            modifier = Modifier.padding(top=5.dp)
        )

        Box(modifier = Modifier.fillMaxHeight(0.8f)){
            ShopList.CartProductList(
                productList = state.cartProducts,
                modifier = Modifier
                )
        }

        ShopButtons.Primary(
            onClick = {
                action.cartToPaymentCompletedAction.invoke()
            },
            text = stringResource(R.string.pay_with_hadi),
        )

        Spacer(modifier = Modifier.padding(bottom=8.dp))

    }
}






