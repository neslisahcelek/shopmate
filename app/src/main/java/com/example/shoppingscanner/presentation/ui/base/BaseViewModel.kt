package com.example.shoppingscanner.presentation.ui.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingscanner.presentation.ui.shared.ShoppingListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class BaseViewModel<S> (
    private val initState: S
) : ViewModel() {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initState)
    val state : StateFlow<S> = _state.asStateFlow()

    open fun onEvent(event:BaseEvent) {}

    fun setState(state: S.() -> S) = viewModelScope.launch {
        _state.update(state)
    }
}