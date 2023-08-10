package com.example.shoppingscanner.presentation.ui.base

import com.example.shoppingscanner.presentation.ui.barcode_scanner.BarcodeScannerEvent

sealed class BaseEvent{
    class GetData(val category:String) : BaseEvent()

    class GetProduct() : BaseEvent()

    data class OnHandledMessage(val message : String) : BaseEvent()
}
