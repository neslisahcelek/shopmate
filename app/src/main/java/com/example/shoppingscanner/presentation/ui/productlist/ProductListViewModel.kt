package com.example.shoppingscanner.presentation.ui.productlist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingscanner.R
import com.example.shoppingscanner.domain.dto.ListProduct
import com.example.shoppingscanner.domain.usecase.GetProductList
import com.example.shoppingscanner.presentation.ui.base.BaseEvent
import com.example.shoppingscanner.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductListUseCase: GetProductList,
) : ViewModel() {

    private val _state = mutableStateOf(ProductListState())
    val state : State<ProductListState> = _state

    var productList: List<ListProduct>? = listOf()

    fun getProductListFromAPI() {
        getProductListUseCase.executeGetProductList().onEach {
            when(it){
                is Resource.Success -> {
                    productList = it.data
                    _state.value = _state.value.copy(
                        productList = productList)
                }
                is Resource.Error -> {
                    println(it.message)
                    _state.value = state.value.copy(error = it.message, messageId = R.string.try_again)
                }
                is Resource.Loading -> {
                    _state.value = state.value.copy(isLoading = true)
                }

                else -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun addToList(currentProduct:ListProduct) {
        val existingProduct = _state.value.shoppingList?.find {
            it.barcode_number == currentProduct?.barcode_number
        }

        if (existingProduct != null) {
            _state.value.copy(messageId = R.string.product_exist)
        } else {
            _state.value = _state.value.copy(
                shoppingList = _state.value.shoppingList?.plus(currentProduct),
                messageId = R.string.product_added
            )
        }

    }

    fun onEvent(event: BaseEvent){
        when (event){
            is BaseEvent.GetData -> {
                getProductListFromAPI()
            }
            is BaseEvent.OnHandledMessage -> {
                _state.value.copy(messageId = null)
            }

            else -> {}
        }
    }

}