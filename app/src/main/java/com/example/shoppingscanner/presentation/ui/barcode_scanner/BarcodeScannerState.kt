package com.example.shoppingscanner.presentation.ui.barcode_scanner

import com.example.shoppingscanner.domain.model.CartProduct

data class BarcodeScannerState(
    val isLoading: Boolean = false,
    val error : String? = "",
    val product : CartProduct? = CartProduct("1234","Nestle Çilekli Çikolata","15.99","",0),
    var totalPrice:Double = 0.0
)
