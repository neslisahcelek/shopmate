package com.example.shoppingscanner.presentation.ui.productlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import com.example.shoppingscanner.presentation.ui.theme.PurplePrimary

val product = CartProduct("1234","Nestle Çilekli Çikolata","15.0","",0)
val product2 = CartProduct("1234","Nestle Çikolata","205.0","",0)
val product3 = CartProduct("1234","dlcld Nestle Çilekli Çikolata","135.0","",0)
val product4 = CartProduct("1234","Eti Çikolata","205.0","",0)
val product5 = CartProduct("1234","dlcld Nestle Çilekli Çikolata","135.0","",0)
val product6 = CartProduct("1234","Eti Çikolata","205.0","",0)


val list = listOf<CartProduct>(product,product2, product3, product4, product5, product6)
@Composable
fun ProductListScreen(
    navController: NavHostController
) {
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

            ShopList.ProductList(
                productList = list,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp))
        }


    }
}

@Preview(showBackground = true)
@Composable
fun ProductListPreview(){
    ProductListScreen(navController = rememberNavController())
}