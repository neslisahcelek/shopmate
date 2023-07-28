package com.example.shoppingscanner.data.remote

import com.example.shoppingscanner.data.remote.dto.ProductResponse
import com.example.shoppingscanner.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface BarcodeService {

    @GET("products")
    suspend fun getProductDetails(
        @Query("barcode") barcode: String = "",
        @Query("key") format: String = Constants.API_KEY
    ): ProductResponse

}
