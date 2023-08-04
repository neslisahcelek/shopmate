package com.example.shoppingscanner.presentation.ui.productlist

import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.component.ShopList
import com.example.shoppingscanner.component.ShopTexts
import com.example.shoppingscanner.domain.dto.CartProduct
import com.example.shoppingscanner.domain.dto.ListProduct
import com.example.shoppingscanner.presentation.ui.barcode_scanner.ProductViewModel
import com.example.shoppingscanner.presentation.ui.base.BaseEvent
import com.example.shoppingscanner.presentation.ui.theme.PurplePrimary
import com.example.shoppingscanner.util.showToast

val product = ListProduct("1234","Nestle Çilekli Çikolata","15.0","","çikolata","nestle")
val product2 = ListProduct("1234","Nestle Çikolata","205.0","","çikolata","nestle")
val product3 = ListProduct("1234","dlcld Nestle Çilekli Çikolata","135.0","","çikolata","nestle")
val product4 = ListProduct("1234","Eti Çikolata","205.0","","çikolata","nestle")
val product5 = ListProduct("1234","dlcld Nestle Çilekli Çikolata","135.0","","çikolata","nestle")
val product6 = ListProduct("1234","Eti Çikolata","205.0","","çikolata","nestle")


val list = listOf<ListProduct>(product,product2, product3, product4, product5, product6)
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
        viewModel.getProductListFromAPI()
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
                text = stringResource(id = R.string.products)
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(PurplePrimary))

            viewModel.state.value.productList?.let {
                ShopList.ProductList(
                    productList = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    onClick = { product ->
                        viewModel.addToList(product)
                    }
                )
            }
        }


    }
}

