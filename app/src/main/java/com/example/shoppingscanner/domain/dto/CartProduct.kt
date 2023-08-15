package com.example.shoppingscanner.domain.dto

data class CartProduct(
    val barcodeNumber: String?,
    val title: String?,
    val price: String? = "10.0",
    val image:String?,
    var quantity: Int
)

