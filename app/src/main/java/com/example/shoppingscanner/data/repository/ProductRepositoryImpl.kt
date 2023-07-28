package com.example.shoppingscanner.data.repository

import com.example.shoppingscanner.data.remote.dto.ProductDto
import com.example.shoppingscanner.domain.repository.ProductRepository
import com.example.shoppingscanner.data.remote.BarcodeService

class ProductRepositoryImpl(
    private val api : BarcodeService
): ProductRepository {
    override suspend fun getProductDetails(barcode: String): ProductDto {
        return api.getProductDetails(barcode=barcode)
    }

}