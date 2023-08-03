package com.example.shoppingscanner.presentation.ui.barcode_scanner

import com.example.shoppingscanner.domain.dto.CartProduct

data class BarcodeScannerState(
    val isLoading: Boolean = false,
    val error: String? = "",
    val cartProducts: List<CartProduct> = mutableListOf(),
    val product: CartProduct? = CartProduct("1234","Nestle Çilekli Çikolata","15.0","",0),
    var totalPrice:Double = 0.0,
    var messageId : Int? = null
)
