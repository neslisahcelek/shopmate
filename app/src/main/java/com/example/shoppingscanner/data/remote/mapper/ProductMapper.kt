package com.example.shoppingscanner.data.remote.mapper

import com.example.shoppingscanner.data.remote.dto.ProductDto
import com.example.shoppingscanner.domain.model.CartProduct

fun ProductDto.toDomainModel(): List<CartProduct>{
    return products!!.map { it ->
        CartProduct(it.barcode_number!!, it.title, it.stores?.get(0)?.price, it.images?.get(0),0)
    }
}