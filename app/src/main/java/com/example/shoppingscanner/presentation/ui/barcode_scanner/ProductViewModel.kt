package com.example.shoppingscanner.presentation.ui.barcode_scanner

import android.app.usage.UsageEvents
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingscanner.R
import com.example.shoppingscanner.domain.repository.BarcodeRepository
import com.example.shoppingscanner.domain.usecase.GetProduct
import com.example.shoppingscanner.presentation.ui.base.BaseEvent
import com.example.shoppingscanner.presentation.ui.base.BaseViewModel
import com.example.shoppingscanner.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductUseCase:GetProduct,
    private val barcodeRepo:BarcodeRepository
) : ViewModel() {

    private val _state = mutableStateOf(BarcodeScannerState())
    val state : State<BarcodeScannerState> = _state

    fun getBarcode(){
        viewModelScope.launch {
            barcodeRepo.scan().collect(){
                if(!it.isNullOrBlank()){
                    println("scan success")
                    getProductFromAPI(it)
                }else{
                    _state.value.copy(
                        messageId = R.string.barcode_not_found
                    )
                }
            }
        }
    }
    fun getProductFromAPI(barcode:String) {
        getProductUseCase.executeGetProduct(barcode = barcode).onEach {
            when(it){
                is Resource.Success -> {
                    val product = it.data?.get(0)
                    _state.value = _state.value.copy(product = product)
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(error = it.message, messageId = R.string.try_again)
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(isLoading = true)
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }



    fun onEvent(event:BaseEvent){
        when (event){
            is BaseEvent.GetData -> {
                getBarcode()
            }
            is BaseEvent.OnHandledMessage -> {
                _state.value.copy(messageId = null)
            }

            else -> {}
        }
    }

    fun addToCart() {
        val currentProduct = state.value.product
        val existingProduct = _state.value.cartProducts.find {
            it.barcode_number == currentProduct!!.barcode_number }

        if (existingProduct != null) {
            existingProduct.quantity++
        } else {
            currentProduct!!.quantity = 1
            _state.value = _state.value.copy(cartProducts = _state.value.cartProducts + currentProduct)
        }

        _state.value = _state.value.copy(totalPrice = calculateTotalPrice())

    }

    private fun calculateTotalPrice(): Double {
        return _state.value.cartProducts.sumOf { it.price!!.toDouble() * it.quantity }
    }

}

