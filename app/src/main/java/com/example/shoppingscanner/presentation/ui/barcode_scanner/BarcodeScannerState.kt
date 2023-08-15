package com.example.shoppingscanner.presentation.ui.barcode_scanner

import com.example.shoppingscanner.domain.dto.CartProduct
import com.example.shoppingscanner.domain.dto.ListProduct

data class BarcodeScannerState(
    val isLoading: Boolean = false,
    val error: String? = "",
    val cartProducts: List<CartProduct?> = mutableListOf(),
    val product: CartProduct? = CartProduct("1","Nestle Çilekli Çikolata","15.0","https://images.migrosone.com/sanalmarket/product/7018051/7018051-b87c99-1650x1650.jpg",0),
    var totalPrice:Double = 0.0,
    var messageId: Int? = null,
    var missingProducts: List<ListProduct> = mutableListOf()
)
