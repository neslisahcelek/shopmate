package com.example.shoppingscanner.di.modules

import com.example.shoppingscanner.data.remote.BarcodeService
import com.example.shoppingscanner.data.repository.ProductRepositoryImpl
import com.example.shoppingscanner.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule{
    @Provides
    @Singleton
    fun providesProductRepository(api: BarcodeService): ProductRepository {
        return ProductRepositoryImpl(api)
    }
}