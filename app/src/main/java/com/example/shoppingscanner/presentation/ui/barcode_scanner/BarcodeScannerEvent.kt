package com.example.shoppingscanner.presentation.ui.barcode_scanner

sealed class BarcodeScannerEvent {
    data class GetData(val barcode : String) : BarcodeScannerEvent()
}