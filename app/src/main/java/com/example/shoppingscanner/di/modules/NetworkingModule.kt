package com.example.shoppingscanner.di.modules

import com.example.shoppingscanner.data.repository.ProductRepositoryImpl
import com.example.shoppingscanner.domain.repository.ProductRepository
import com.example.shoppingscanner.data.remote.BarcodeService
import com.example.shoppingscanner.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesBarcodeService(retrofit: Retrofit): BarcodeService {
        return retrofit.create(BarcodeService::class.java)
    }

    @Singleton
    @Provides
    fun provideBasicOkHttpClient(
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        return builder.build()
    }


}