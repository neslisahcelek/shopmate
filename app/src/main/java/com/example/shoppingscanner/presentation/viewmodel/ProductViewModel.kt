package com.example.shoppingscanner.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingscanner.data.remote.dto.CartProduct
import com.example.shoppingscanner.data.remote.dto.Product
import com.example.shoppingscanner.domain.repository.BarcodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
   private val barcodeRepository: BarcodeRepository
) : ViewModel() {

    val cartProducts = HashMap<Product,Int>()

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product>
        get() = _product

    var product2 = mutableStateOf<List<CartProduct>>(listOf())



    fun getDataFromAPI(barcode:String) {
        viewModelScope.launch {
            Log.d("get data from api", "launch")
            val response = barcodeRepository.getProductDetails(barcode=barcode)

        }
    }
}