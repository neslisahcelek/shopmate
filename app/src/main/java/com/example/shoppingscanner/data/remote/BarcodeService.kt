package com.example.shoppingscanner.data.remote

import com.example.shoppingscanner.data.remote.model.ProductResponse
import com.example.shoppingscanner.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface BarcodeService {

    @GET("products")
    suspend fun getProductDetails(
        @Query("barcode") barcode: String = "",
        @Query("key") format: String = Constants.API_KEY
    ): ProductResponse

    @GET("products")
    suspend fun getProductList(
        @Query("category") category: String = "shoes",
        @Query("brand") brand: String = "adidas",
        @Query("key") format: String = Constants.API_KEY
    ): ProductResponse

}
