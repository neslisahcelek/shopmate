package com.example.shoppingscanner.di.manager

import com.example.shoppingscanner.data.remote.dto.ProductResponse
import com.example.shoppingscanner.domain.repository.BarcodeRepository
import com.example.shoppingscanner.data.remote.BarcodeService

class BarcodeRepositoryImpl(
    private val api : BarcodeService
): BarcodeRepository {
    override suspend fun getProductDetails(barcode: String): ProductResponse {
        return api.getProductDetails(barcode=barcode)
    }

}