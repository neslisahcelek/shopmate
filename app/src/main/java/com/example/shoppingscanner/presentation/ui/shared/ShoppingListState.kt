package com.example.shoppingscanner.presentation.ui.shared

import com.example.shoppingscanner.domain.dto.ListProduct

data class ShoppingListState(
    var shoppingList: List<ListProduct> = mutableListOf(),
)