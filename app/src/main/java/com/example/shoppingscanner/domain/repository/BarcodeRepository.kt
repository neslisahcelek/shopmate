package com.example.shoppingscanner.domain.repository

import kotlinx.coroutines.flow.Flow

interface BarcodeRepository {
    fun scan(): Flow<String?>
}