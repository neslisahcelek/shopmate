package com.example.shoppingscanner.network

import com.example.shoppingscanner.model.ProductResponse
import javax.inject.Inject

interface BarcodeRepository {
    suspend fun getProductDetails(barcode:String):ProductResponse
}