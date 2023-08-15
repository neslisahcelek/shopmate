package com.example.shoppingscanner.data.remote.mapper

import com.example.shoppingscanner.data.remote.model.ProductResponse
import com.example.shoppingscanner.domain.dto.CartProduct

fun ProductResponse.toCartProduct(): List<CartProduct>{
    return products?.map {
        CartProduct(
            barcodeNumber = it.barcode_number,
            title = it.title, it.stores?.first()?.price,
            image = it.images?.first(),
            quantity = 0)
    } ?: emptyList()
}