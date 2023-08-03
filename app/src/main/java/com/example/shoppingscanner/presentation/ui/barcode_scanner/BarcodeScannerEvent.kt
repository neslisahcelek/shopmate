package com.example.shoppingscanner.presentation.ui.barcode_scanner

sealed class BarcodeScannerEvent {
    class GetData() : BarcodeScannerEvent()

    data class OnHandledMessage(val message : String) : BarcodeScannerEvent()
}