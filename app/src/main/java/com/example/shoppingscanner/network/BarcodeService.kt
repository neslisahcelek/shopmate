package com.example.shoppingscanner.network

import com.example.shoppingscanner.model.ProductResponse
import com.example.shoppingscanner.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BarcodeService {

    @GET("products")
    suspend fun getProductDetails(
        @Query("barcode") barcode: String = "",
        @Query("key") format: String = Constants.API_KEY
    ): ProductResponse

}
