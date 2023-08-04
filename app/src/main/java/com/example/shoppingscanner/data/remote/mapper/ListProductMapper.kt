package com.example.shoppingscanner.data.remote.mapper

import com.example.shoppingscanner.data.remote.model.ProductResponse
import com.example.shoppingscanner.domain.dto.CartProduct
import com.example.shoppingscanner.domain.dto.ListProduct

fun ProductResponse.toListProduct(): List<ListProduct>{
    return products!!.map { it ->
        ListProduct(
            it.barcode_number!!,
            it.title,
            it.stores?.get(0)?.price,
            it.images?.get(0),
            it.category,
            it.brand)
    }
}