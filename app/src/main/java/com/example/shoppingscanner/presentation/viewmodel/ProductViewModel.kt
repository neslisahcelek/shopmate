package com.example.shoppingscanner.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingscanner.data.remote.dto.Product
import com.example.shoppingscanner.domain.model.CartProduct
import com.example.shoppingscanner.domain.usecase.GetProduct
import com.example.shoppingscanner.presentation.ui.barcode_scanner.BarcodeScannerEvent
import com.example.shoppingscanner.presentation.ui.barcode_scanner.BarcodeScannerState
import com.example.shoppingscanner.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
   private val getProductUseCase:GetProduct
) : ViewModel() {

    private val _state = mutableStateOf<BarcodeScannerState>(BarcodeScannerState())
    val state : State<BarcodeScannerState> = _state





    fun getDataFromAPI(barcode:String) {
        println("get data from api")
        getProductUseCase.executeGetProduct(barcode = barcode).onEach {
            when(it){
                is Resource.Success -> {
                    println("get data from api success")
                    _state.value = BarcodeScannerState(product = it.data?.get(0))
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(error = it.message)
                    println("get data from api error" + it.message)
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event:BarcodeScannerEvent){
        when (event){
            is BarcodeScannerEvent.GetData -> {
                getDataFromAPI(event.barcode)
            }
        }
    }
}

