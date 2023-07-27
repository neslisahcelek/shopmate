package com.example.shoppingscanner.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingscanner.R
import com.example.shoppingscanner.model.CartProduct

object ShopList {
    @Composable
    fun ProductList(
        productList : List<CartProduct>
    ) {
        LazyColumn(
            contentPadding = PaddingValues(5.dp)
        ){
            items(productList){ product ->
                ProductRow(product = product)

            }
        }
    }

    @Composable
    fun ProductRow(
        product : CartProduct
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(start = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.purpledot),
                contentDescription = "barcode",
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
                )
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.barcode),
                contentDescription = "barcode",
                modifier = Modifier
                    .padding(top = 3.dp, bottom = 3.dp, start = 15.dp)
                    .width(40.dp)
                    .height(50.dp)
            )
            product.title?.let {
                ShopTexts.BodyRegular(
                    text = it,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start=30.dp)
                )
            }
            product.price?.let {
                ShopTexts.BodyBold(
                    text = it + " ₺",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 120.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductRowPreview(){
    ShopList.ProductRow(
        product = CartProduct("Süt","15.0","",1)
    )
}









