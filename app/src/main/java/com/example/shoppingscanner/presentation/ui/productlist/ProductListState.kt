package com.example.shoppingscanner.presentation.ui.productlist

import com.example.shoppingscanner.domain.dto.CartProduct
import com.example.shoppingscanner.domain.dto.ListProduct

data class ProductListState (
    val isLoading: Boolean = false,
    val error: String? = "",
    val productList: List<ListProduct>? = mutableListOf(),
    val shoppingList: List<ListProduct>? = mutableListOf(),
    var messageId : Int? = null
)