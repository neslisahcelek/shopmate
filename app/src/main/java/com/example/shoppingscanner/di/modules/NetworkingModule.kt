package com.example.shoppingscanner.di.modules

import com.example.shoppingscanner.di.manager.BarcodeRepositoryImpl
import com.example.shoppingscanner.domain.repository.BarcodeRepository
import com.example.shoppingscanner.data.remote.BarcodeService
import com.example.shoppingscanner.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkingModule {
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun providesBarcodeService(retrofit: Retrofit): BarcodeService {
        return retrofit.create(BarcodeService::class.java)
    }

    @Provides
    @Singleton
    fun providesBarcodeRepository(api: BarcodeService): BarcodeRepository {
        return BarcodeRepositoryImpl(api)
    }
}