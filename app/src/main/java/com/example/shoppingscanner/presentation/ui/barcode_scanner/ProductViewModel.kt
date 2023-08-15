package com.example.shoppingscanner.presentation.ui.barcode_scanner

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.shoppingscanner.R
import com.example.shoppingscanner.domain.repository.BarcodeRepository
import com.example.shoppingscanner.domain.usecase.GetProduct
import com.example.shoppingscanner.presentation.ui.base.BaseEvent
import com.example.shoppingscanner.presentation.ui.base.BaseViewModel
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
    ) : BaseViewModel<BarcodeScannerState>(BarcodeScannerState()) {

    var shoppingListState: State<ShoppingListState> = sharedViewModel.shoppingListState

    override fun onEvent(event:BaseEvent){
        when (event){
            is BaseEvent.GetData -> {

            }
            is BaseEvent.GetProduct -> {
                getBarcode()
            }
            is BaseEvent.OnHandledMessage -> {
                setState {
                    copy(messageId = null)
                }
            }
        }
    }

    fun getBarcode(){
        viewModelScope.launch {
            barcodeRepo.scan().collect(){
                if(!it.isNullOrBlank()){
                    getProductFromAPI(it)
                }else{
                    setState {
                        copy(messageId = R.string.barcode_not_found)
                    }
                }
            }
        }
    }
    fun getProductFromAPI(barcode:String) {
        getProductUseCase.executeGetProduct(barcode = barcode).onEach {
            when(it){
                is Resource.Success -> {
                    val product = it.data?.first()
                    setState {
                        copy(product = product)
                    }
                }
                is Resource.Error -> {
                    setState {
                        copy(
                            error = it.message,
                            messageId = R.string.try_again
                        )
                    }
                }
                is Resource.Loading -> {
                    setState {
                        copy(isLoading = true)
                    }                }
            }
        }.launchIn(viewModelScope)
    }

    fun addToCart() {
        val currentProduct = state.value.product
        val existingProduct = state.value.cartProducts.find {
            it?.barcode_number == currentProduct?.barcode_number
        }

        if (existingProduct != null) {
            existingProduct.quantity++
        } else {
            currentProduct?.quantity = 1
            setState {
                copy(
                    cartProducts = state.value.cartProducts.plus(currentProduct)
                )
            }
            checkShoppingList()
        }
        setState {
            copy(
                totalPrice = calculateTotalPrice()
            )
        }
    }

    fun checkShoppingList(){
        val cartProducts = state.value.cartProducts
        var shopList = shoppingListState.value.shoppingList
        shopList?.let { shoppingList ->
            for (productInList in shoppingList) {
                val productFoundInCart = cartProducts.find {
                    it?.barcode_number == productInList.barcode_number
                }

                if (productFoundInCart == null) {
                    productInList.isInCart = false
                    setState {
                        copy(
                            missingProducts=state.value.missingProducts.plus(productInList)
                        )
                    }
                }else{
                    productInList.isInCart = true
                }
            }
        }
    }

    private fun calculateTotalPrice(): Double =
        state.value.cartProducts.sumOf { it?.price!!.toDouble() * it.quantity }

}

