package com.example.shoppingscanner.data.remote.mapper

import com.example.shoppingscanner.data.remote.model.ProductResponse
import com.example.shoppingscanner.domain.dto.ListProduct

fun ProductResponse.toListProduct(): List<ListProduct>{
    return products?.map {
        ListProduct(
            barcode_number = it.barcode_number,
            title = it.title,
            price = it.stores?.first()?.price,
            image = it.images?.first(),
            category = it.category,
            brand = it.brand)
    } ?: emptyList()
}