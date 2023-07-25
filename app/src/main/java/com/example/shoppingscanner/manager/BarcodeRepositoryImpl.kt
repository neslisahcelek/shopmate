package com.example.shoppingscanner.manager

import com.example.shoppingscanner.model.ProductResponse
import com.example.shoppingscanner.network.BarcodeRepository
import com.example.shoppingscanner.network.BarcodeService

class BarcodeRepositoryImpl(
    private val api : BarcodeService
): BarcodeRepository{
    override suspend fun getProductDetails(barcode: String): ProductResponse {
        return api.getProductDetails(barcode=barcode)
    }

}