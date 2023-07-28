package com.example.shoppingscanner.domain.model

data class CartProduct(
    val barcode_number: String,
    val title: String?,
    val price: String?,
    val image:String?,
    var quantity: Int
)

