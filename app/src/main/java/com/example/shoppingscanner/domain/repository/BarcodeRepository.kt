package com.example.shoppingscanner.domain.repository

import com.example.shoppingscanner.data.remote.dto.ProductResponse

interface BarcodeRepository {
    suspend fun getProductDetails(barcode:String): ProductResponse
}