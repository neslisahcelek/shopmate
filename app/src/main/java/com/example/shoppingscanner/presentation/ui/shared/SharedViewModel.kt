package com.example.shoppingscanner.presentation.ui.shared

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var shoppingListState: MutableState<ShoppingListState> = mutableStateOf(ShoppingListState())
}