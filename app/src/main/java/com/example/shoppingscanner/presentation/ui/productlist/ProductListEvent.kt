package com.example.shoppingscanner.presentation.ui.productlist

sealed class ProductListEvent {
    object RemoveProduct : ProductListEvent()
}