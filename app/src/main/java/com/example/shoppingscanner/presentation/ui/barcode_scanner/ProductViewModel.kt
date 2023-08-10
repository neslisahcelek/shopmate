package com.example.shoppingscanner.presentation.ui.barcode_scanner

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingscanner.R
import com.example.shoppingscanner.domain.repository.BarcodeRepository
import com.example.shoppingscanner.domain.usecase.GetProduct
import com.example.shoppingscanner.presentation.ui.base.BaseEvent
import com.example.shoppingscanner.presentation.ui.productlist.ProductListState
import com.example.shoppingscanner.presentation.ui.shared.SharedViewModel
import com.example.shoppingscanner.presentation.ui.shared.ShoppingListState
import com.example.shoppingscanner.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductUseCase:GetProduct,
    private val barcodeRepo:BarcodeRepository,
    private val sharedViewModel: SharedViewModel,
    ) : ViewModel() {

    private val _state = mutableStateOf(BarcodeScannerState())
    val state : State<BarcodeScannerState> = _state

    private val _productListState = mutableStateOf(ProductListState())
    val productListState : State<ProductListState> = _productListState

    var shoppingListState: State<ShoppingListState> = sharedViewModel.shoppingListState


    fun onEvent(event:BaseEvent){
        when (event){
            is BaseEvent.GetData -> {

            }
            is BaseEvent.GetProduct -> {
                getBarcode()
            }
            is BaseEvent.OnHandledMessage -> {
                _state.value.copy(messageId = null)
            }
        }
    }

    fun getBarcode(){
        println(productListState.value.productList?.size)
        viewModelScope.launch {
            barcodeRepo.scan().collect(){
                if(!it.isNullOrBlank()){
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
            }
        }.launchIn(viewModelScope)
    }

    fun addToCart() {
        val currentProduct = state.value.product
        val existingProduct = _state.value.cartProducts.find {
            it.barcode_number == currentProduct!!.barcode_number }

        if (existingProduct != null) {
            existingProduct.quantity++
        } else {
            currentProduct!!.quantity = 1
            _state.value = _state.value.copy(
                cartProducts = _state.value.cartProducts + currentProduct
            )
            checkShoppingList()
        }

        _state.value = _state.value.copy(totalPrice = calculateTotalPrice())

    }

    fun checkShoppingList(){
        val cartProducts = state.value.cartProducts
        var shopList = shoppingListState.value.shoppingList
        shopList?.size?.let {
            if(it <= cartProducts.size){
                shopList?.let { shoppingList ->
                    for (productInList in shoppingList) {
                        val productFoundInCart = cartProducts.find {
                            it.barcode_number == productInList.barcode_number
                        }

                        if (productFoundInCart == null) {
                            productInList.isInCart = false
                            _state.value = state.value.copy(
                                missingProducts=state.value.missingProducts + productInList
                            )
                        }else{
                            productInList.isInCart = true
                        }
                    }
                }
            }
        }
    }

    private fun calculateTotalPrice(): Double {
        return _state.value.cartProducts.sumOf { it.price!!.toDouble() * it.quantity }
    }

}

