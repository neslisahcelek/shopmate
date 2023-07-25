package com.example.shoppingscanner.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingscanner.model.Product
import com.example.shoppingscanner.model.ProductResponse
import com.example.shoppingscanner.network.BarcodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
   private val barcodeRepository: BarcodeRepository
) : ViewModel() {

    val cartProducts = HashMap< Product,Int>()

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product>
        get() = _product

    private val _productResponse = MutableLiveData<ProductResponse>()
    val productResponse: LiveData<ProductResponse>
        get() = _productResponse



    fun getDataFromAPI(barcode:String) {
        viewModelScope.launch {
            Log.d("get data from api", "launch")
            try {
                Log.d(ContentValues.TAG, "launch: $barcode")
                val response = barcodeRepository.getProductDetails(barcode=barcode)
                _productResponse.value=response
                _product.value = response.products?.get(0)

            } catch (e: Exception) {
                e.message?.let { Log.d("api error", it) }
            }
        }
    }
}