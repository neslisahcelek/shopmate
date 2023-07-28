package com.example.shoppingscanner.domain.repository

import com.example.shoppingscanner.data.remote.dto.ProductDto

interface ProductRepository {
    suspend fun getProductDetails(barcode:String): ProductDto
}