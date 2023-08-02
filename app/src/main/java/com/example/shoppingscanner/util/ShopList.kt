package com.example.shoppingscanner.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppingscanner.R
import com.example.shoppingscanner.domain.model.CartProduct


object ShopList {
    @Composable
    fun ProductList(
        productList : List<CartProduct>,
        modifier: Modifier
    ) {
        LazyColumn(
            contentPadding = PaddingValues(5.dp),
            modifier = modifier
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
                contentDescription = "",
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
                )
            Spacer(modifier = Modifier.width(15.dp))
            AsyncImage(
                model = product.image,
                contentDescription = "Product image",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(top = 3.dp, bottom = 3.dp)
                    .width(50.dp)
                    .height(50.dp),
            )
            Spacer(modifier = Modifier.width(25.dp))

            product.title?.let {
                ShopTexts.BodyRegular(
                    text = it,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.width(190.dp)

                )
            }
            if (product.quantity == 1) {
                product.price?.let {
                    ShopTexts.BodyBold(
                        text = it + " ₺",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start,
                    )
                }
            } else {
                val totalPrice = (product.price!!.toDouble() * product.quantity).toString() + " ₺"
                ShopTexts.BodyBold(
                    text = totalPrice,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductRowPreview(){
    ShopList.ProductRow(
        product = CartProduct("1233","Süt", "15.0", "", 1)
    )
}









