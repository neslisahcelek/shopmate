package com.example.shoppingscanner.presentation.ui.barcode_scanner

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingscanner.data.remote.dto.Product
import com.example.shoppingscanner.domain.model.CartProduct
import com.example.shoppingscanner.domain.repository.BarcodeRepository
import com.example.shoppingscanner.domain.usecase.GetProduct
import com.example.shoppingscanner.presentation.ui.barcode_scanner.BarcodeScannerEvent
import com.example.shoppingscanner.presentation.ui.barcode_scanner.BarcodeScannerState
import com.example.shoppingscanner.util.Resource
import com.example.shoppingscanner.util.showToast
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
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
                    _state.value = _state.value.copy(product = it.data?.get(0))
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
        val product = state.value.product
        val updatedProduct = product?.copy(quantity = (product.quantity + 1))
        println("updated product: " + updatedProduct!!.title)

        _state.value = state.value.copy(product = updatedProduct)

        updatedProduct?.let {
            _state.value.totalPrice = it.price?.toDouble()?.times(it.quantity)!!
            println("updated total: " + _state.value.totalPrice)
        }

    }

}

