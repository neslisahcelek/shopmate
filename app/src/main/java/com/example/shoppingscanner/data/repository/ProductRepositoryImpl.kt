package com.example.shoppingscanner.data.repository

import com.example.shoppingscanner.data.remote.BarcodeService
import com.example.shoppingscanner.data.remote.model.ProductResponse
import com.example.shoppingscanner.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val api : BarcodeService
): ProductRepository {
    override suspend fun getProductDetails(barcode: String): ProductResponse {
        return api.getProductDetails(barcode=barcode)
    }

    override suspend fun getProductList(category:String): ProductResponse {
        return api.getProductList(category)
    }
}