package com.example.shoppingscanner.di.modules

import android.app.Application
import android.content.Context
import com.example.shoppingscanner.data.remote.BarcodeService
import com.example.shoppingscanner.data.repository.BarcodeRepositoryImpl
import com.example.shoppingscanner.data.repository.ProductRepositoryImpl
import com.example.shoppingscanner.domain.repository.BarcodeRepository
import com.example.shoppingscanner.domain.repository.ProductRepository
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
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

    @Provides
    fun providesBarcodeRepository(scanner: GmsBarcodeScanner): BarcodeRepository{
        return BarcodeRepositoryImpl(scanner)
    }

    @Provides
    fun providesOptions(): GmsBarcodeScannerOptions{
        return GmsBarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .build()
    }
    @Provides
    fun providesContext(application:Application): Context{
        return return application.applicationContext
    }
    @Provides
    fun providesScanner(context:Context,options: GmsBarcodeScannerOptions): GmsBarcodeScanner{
        return GmsBarcodeScanning.getClient(context,options)
    }
}