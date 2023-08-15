package com.example.shoppingscanner.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.shoppingscanner.R
import com.example.shoppingscanner.domain.dto.CartProduct
import com.example.shoppingscanner.domain.dto.ListProduct
import com.example.shoppingscanner.presentation.ui.productlist.ProductListViewModel
import com.example.shoppingscanner.presentation.ui.theme.Purple80
import com.example.shoppingscanner.util.ProductCategory


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
        product: ListProduct,
        onClick: () -> Unit
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = BorderStroke(2.dp, Purple80),
            shape = MaterialTheme.shapes.extraSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                ) {
                    AsyncImage(
                        model = product.image,
                        contentDescription = "Product image",
                        placeholder = painterResource(id = R.drawable.baseline_image_24),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .padding(top = 20.dp, bottom = 3.dp)
                            .fillMaxSize()
                    )

                    ShopButtons.AddList(
                        onClick = onClick,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.title ?: "",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${product.price ?: ""} ₺",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.End)
                )
            }
        }
    }
    @Composable
    fun BottomSheetProductList(
        productList : List<ListProduct>,
        modifier: Modifier,
        viewModel: ProductListViewModel
    ) {
        LazyColumn(
            contentPadding = PaddingValues(5.dp),
            modifier = modifier
        ){
            items(productList){ product ->
                BottomSheetProductRow(
                    product = product,
                    viewModel = viewModel
                )
            }
        }
    }

    @Composable
    fun BottomSheetProductRow(
        product : ListProduct,
        viewModel: ProductListViewModel
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 8.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                bitmap = ImageBitmap.imageResource(id = R.drawable.purpledot),
                contentDescription = "",
                modifier = Modifier
                    .width(10.dp)
                    .height(10.dp),
            )
            product.title?.let {
                ShopTexts.BodyRegular(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(start = 5.dp, end = 16.dp),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                )
            }
            IconButton(
                onClick = {
                    viewModel.removeFromList(product)
                },
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete button")
            }
        }
    }
    @Composable
    fun ShoppingProductList(
        productList : List<ListProduct>,
        modifier: Modifier
    ) {
        LazyColumn(
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
                .padding(start = 8.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ){
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
                    product.isInCart = newCheckedValue
                }
            )
        }
    }
    @Composable
    fun CategoryList(
        modifier: Modifier,
        viewModel : ProductListViewModel
    ) {
        val selectedCategory = remember { mutableStateOf("") }
        LazyRow(
            contentPadding = PaddingValues(),
            modifier = modifier
        ){
            val categoryList = mutableListOf(
                ProductCategory.DECORATION.categoryName,
                ProductCategory.CHAIR.categoryName,
                ProductCategory.JACKET.categoryName,
                ProductCategory.LUGGAGE.categoryName,
                ProductCategory.SHOE.categoryName,
            )
            items(categoryList){ category ->
                CategoryItem(
                    category = category,
                    viewModel = viewModel,
                    selectedCategory = selectedCategory,
                )
            }
        }
    }

    @Composable
    fun CategoryItem(
        category : String,
        viewModel : ProductListViewModel,
        selectedCategory: MutableState<String>
    ){
        val isSelected = selectedCategory.value == category

        ShopButtons.Category(
            onClick ={
                selectedCategory.value = category
                viewModel.getProductListFromAPI(category)
            },
            text = category,
            modifier = Modifier
                .padding(horizontal = 5.dp),
            isSelected = isSelected
        )
    }

    @Composable
    fun CartProductList(
        productList: List<CartProduct?>,
        modifier: Modifier
    ) {
        LazyColumn(
            contentPadding = PaddingValues(5.dp),
            modifier = modifier
        ){
            items(productList){ product ->
                product?.let {
                    CartProductRow(product = product)
                }
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

    val list = listOf<ListProduct>(
        ListProduct("1", "Nestle Çilekli Çikolata", "15.0", "https://example.com/image1.jpg", "Çikolata", "Nestle"),
        ListProduct("2", "Eti Bademli Çikolata", "25.0", "https://example.com/image2.jpg", "Çikolata", "Eti"),
        ListProduct("3", "Milka Sütlü Çikolata", "10.0", "https://example.com/image3.jpg", "Çikolata", "Milka"),
        ListProduct("4", "Ülker Bitter Çikolata", "18.5", "https://example.com/image4.jpg", "Çikolata", "Ülker"),
        ListProduct("5", "Godiva Fındıklı Çikolata", "40.0", "https://example.com/image5.jpg", "Çikolata", "Godiva"),
        ListProduct("6", "Cadbury Karamelli Çikolata", "12.75", "https://example.com/image6.jpg", "Çikolata", "Cadbury"),
        ListProduct("7", "Lindt Bitter Portakallı Çikolata", "30.0", "https://example.com/image7.jpg", "Çikolata", "Lindt"),
        ListProduct("8", "Toblerone Bademli Çikolata", "22.5", "https://example.com/image8.jpg", "Çikolata", "Toblerone"),
        ListProduct("9", "Anthon Berg Çikolatalı Likör", "50.0", "https://example.com/image9.jpg", "Çikolata", "Anthon Berg"),
        ListProduct("10", "Ritter Sport Fıstıklı Çikolata", "14.0", "https://example.com/image10.jpg", "Çikolata", "Ritter Sport"),
        ListProduct("11", "Ferrero Rocher Findik", "28.5", "https://example.com/image11.jpg", "Çikolata", "Ferrero")

    )
    //ShopList.CategoryList(modifier = Modifier.fillMaxSize())
}








