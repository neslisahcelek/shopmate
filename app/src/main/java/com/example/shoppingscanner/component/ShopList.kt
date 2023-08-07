package com.example.shoppingscanner.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.shoppingscanner.component.ShopList.ProductList
import com.example.shoppingscanner.component.ShopList.ShoppingProductList
import com.example.shoppingscanner.domain.dto.CartProduct
import com.example.shoppingscanner.domain.dto.ListProduct


object ShopList {
    @Composable
    fun ProductList(
        productList : List<ListProduct>,
        modifier: Modifier,
        onClick:(ListProduct) -> Unit
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 5.dp),
            modifier = modifier
        ){
            items(productList){ product ->
                ProductItem(
                    product = product,
                    onClick = { onClick(product) }
                )
            }
        }
    }

    @Composable
    fun ProductItem(
        product : ListProduct,
        onClick: () -> Unit
    ){
        Column (
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
            ){
                Image(
                    bitmap = ImageBitmap
                        .imageResource(id = R.drawable.shoppingcar),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight()
                        .padding(
                            horizontal = 8.dp
                        ),
                    alignment = Alignment.Center,
                    contentDescription = "",
                )

                ShopButtons.AddList(
                    onClick = onClick,
                    modifier = Modifier.align(Alignment.TopEnd)

                )

            }


            product.title?.let {
                ShopTexts.BodyBold(
                    text = it,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 5.dp
                        ),
                    textAlign = TextAlign.Center,
                    maxLines = 2

                )
            }
            product.price?.let {
                ShopTexts.BodyBold(
                    text = it +" ₺",
                    fontSize = 16.sp,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 5.dp)
                )
            }
        }
    }

    @Composable
    fun ShoppingProductList(
        productList : List<ListProduct>,
        modifier: Modifier
    ) {
        LazyColumn(
            contentPadding = PaddingValues(5.dp),
            modifier = modifier
        ){
            items(productList){ product ->
                ShoppingProductRow(
                    product = product)
            }
        }
    }

    @Composable
    fun ShoppingProductRow(
        product : ListProduct,
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(start = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.purpledot),
                contentDescription = "",
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
            )
            Spacer(modifier = Modifier.width(15.dp))

            product.title?.let {
                ShopTexts.BodyRegular(
                    text = it,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.width(190.dp)

                )
            }
            Checkbox(
                checked = product.isInCart ?: false,
                onCheckedChange = { newCheckedValue ->
                }
            )
        }
    }

    @Composable
    fun CartProductList(
        productList : List<CartProduct>,
        modifier: Modifier
    ) {
        LazyColumn(
            contentPadding = PaddingValues(5.dp),
            modifier = modifier
        ){
            items(productList){ product ->
                CartProductRow(product = product)

            }
        }
    }

    @Composable
    fun CartProductRow(
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



@Preview(showBackground =true)
@Composable
fun ProductItemPreview(){
    val product = CartProduct("1234","Nestle Çilekli Çikolata","15.0","",0)
    val product2 = CartProduct("1234","Nestle Çikolata","205.0","",0)

    val product3 = ListProduct("1234","dlcld Nestle Çilekli Çikolata","135.0","","çikolata","nestle")
    val product4 = ListProduct("1234","Eti Çikolata","205.0","","çikolata","nestle")
    val product5 = ListProduct("1234","dlcld Nestle Çilekli Çikolata","135.0","","çikolata","nestle")
    val product6 = ListProduct("1234","Eti Çikolata","205.0","","çikolata","nestle")

    val list = listOf<ListProduct>(product3,product4)
    ShoppingProductList(list, modifier = Modifier.fillMaxSize())
}








