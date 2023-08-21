package com.example.shoppingscanner.presentation.ui.productlist

import com.example.shoppingscanner.domain.dto.ListProduct

data class ProductListState (
    val isLoading: Boolean = false,
    val error: String? = "",
    val productList: List<ListProduct>? = mutableListOf(
        ListProduct("1", "Nestle Çilekli Çikolata", "15.0", "https://images.migrosone.com/sanalmarket/product/7018051/7018051-b87c99-1650x1650.jpg", "Çikolata", "Nestle"),
        ListProduct("2", "Eti Bademli Çikolata dldsşlvdmvdşlfv lfşmeknvşknnşvkde kvdenşvknv kşvneşkveşevnwknek knekwnveneevnnevwknkşes", "25.0", "https://images.migrosone.com/sanalmarket/product/7018051/7018051-b87c99-1650x1650.jpg", "Çikolata", "Eti"),
        ListProduct("3", "Milka Sütlü Çikolata", "10.0", "https://images.migrosone.com/sanalmarket/product/7018051/7018051-b87c99-1650x1650.jpg", "Çikolata", "Milka"),
        ListProduct("4", "Ülker Bitter Çikolata", "18.5", "https://example.com/image4.jpg", "Çikolata", "Ülker"),
        ListProduct("5", "Godiva Fındıklı Çikolata", "40.0", "https://example.com/image5.jpg", "Çikolata", "Godiva"),
        ListProduct("6", "Cadbury Karamelli Çikolata", "12.75", "https://example.com/image6.jpg", "Çikolata", "Cadbury"),
        ListProduct("7", "Lindt Bitter Portakallı Çikolata", "30.0", "https://example.com/image7.jpg", "Çikolata", "Lindt"),
        ListProduct("8", "Toblerone Bademli Çikolata", "22.5", "https://example.com/image8.jpg", "Çikolata", "Toblerone"),
        ListProduct("9", "Anthon Berg Çikolatalı Likör", "50.0", "https://example.com/image9.jpg", "Çikolata", "Anthon Berg"),
        ListProduct("10", "Ritter Sport Fıstıklı Çikolata", "14.0", "https://example.com/image10.jpg", "Çikolata", "Ritter Sport"),
        ListProduct("11", "Ferrero Rocher Findik", "28.5", "https://example.com/image11.jpg", "Çikolata", "Ferrero")


    ),
    val shoppingList: List<ListProduct>? = mutableListOf(
        ListProduct("1234","dlcld Nestle Çilekli Çikolata","135.0","","çikolata","nestle") ,


        ),
    var messageId : Int? = null
)

sealed class ProductListEvent {

}