package com.example.shoppingscanner.presentation.ui.barcode_scanner

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingscanner.domain.repository.BarcodeRepository
import com.example.shoppingscanner.domain.usecase.GetProduct
import com.example.shoppingscanner.util.Resource
import com.example.shoppingscanner.util.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getProductUseCase:GetProduct,
    private val barcodeRepo:BarcodeRepository
) : ViewModel() {

    private val _state = mutableStateOf<BarcodeScannerState>(BarcodeScannerState())
    val state : State<BarcodeScannerState> = _state

    fun getBarcode(){
        println("getbarcode")
        viewModelScope.launch {
            barcodeRepo.scan().collect(){
                println("getbarcode scan")
                if(!it.isNullOrBlank()){
                    println("barcode not null")
                    getDataFromAPI(it)
                }else{
                    showToast(context,"Barkod bulunamadı.")
                }
            }
        }
    }
    fun getDataFromAPI(barcode:String) {
        println("get data from api")
        getProductUseCase.executeGetProduct(barcode = barcode).onEach {
            when(it){
                is Resource.Success -> {
                    val product = it.data?.get(0)
                    _state.value = _state.value.copy(product = product)
                    println("get data from api success new product: " + state.value.product!!.title)
                }

                is Resource.Error -> {
                    _state.value = state.value.copy(error = it.message)
                    showToast(context,"Lütfen tekrar deneyin.")
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

    fun addToCart() {
        val currentProduct = state.value.product
        val existingProduct = _state.value.cartProducts.find {
            it.barcode_number == currentProduct!!.barcode_number }

        if (existingProduct != null) {
            existingProduct.quantity++
            //existingProduct.price = (existingProduct.price!!.toDouble() *2).toString()
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

