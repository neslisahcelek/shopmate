package com.example.shoppingscanner.data.remote.mapper

import com.example.shoppingscanner.data.remote.model.ProductResponse
import com.example.shoppingscanner.domain.dto.CartProduct

fun ProductResponse.toCartProduct(): List<CartProduct>{
    return products!!.map { it ->
        CartProduct(
            it.barcode_number!!,
            it.title, it.stores?.get(0)?.price,
            it.images?.get(0),
            0)
    }
}