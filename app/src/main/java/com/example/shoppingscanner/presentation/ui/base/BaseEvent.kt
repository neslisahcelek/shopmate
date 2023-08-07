package com.example.shoppingscanner.presentation.ui.base

import com.example.shoppingscanner.presentation.ui.barcode_scanner.BarcodeScannerEvent

sealed class BaseEvent{
    class GetData() : BaseEvent()

    data class OnHandledMessage(val message : String) : BaseEvent()
    class ShowListInfo() : BaseEvent()
}
