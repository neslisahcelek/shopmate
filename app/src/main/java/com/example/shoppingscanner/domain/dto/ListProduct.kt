package com.example.shoppingscanner.domain.dto

data class ListProduct(
    val barcodeNumber: String?,
    val title: String?,
    val price: String?,
    val image:String?,
    val category:String?,
    val brand:String?,
    var isInCart:Boolean? = false
)
