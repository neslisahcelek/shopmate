package com.example.shoppingscanner.presentation.ui.base

import com.example.shoppingscanner.presentation.ui.barcode_scanner.BarcodeScannerEvent
import com.example.shoppingscanner.presentation.ui.productlist.ProductListEvent

sealed class BaseEvent{
    class GetData(val category:String) : BaseEvent()

    class GetProduct() : BaseEvent()

    data class OnHandledMessage(val message : String) : BaseEvent()

    object RemoveProduct : BaseEvent()
}
