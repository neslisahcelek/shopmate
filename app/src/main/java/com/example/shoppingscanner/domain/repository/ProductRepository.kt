package com.example.shoppingscanner.domain.repository

import com.example.shoppingscanner.data.remote.model.ProductResponse

interface ProductRepository {
    suspend fun getProductDetails(barcode:String): ProductResponse
    suspend fun getProductList(category: String): ProductResponse
}