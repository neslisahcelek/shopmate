package com.example.shoppingscanner.presentation.ui.productlist

import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.shoppingscanner.R
import com.example.shoppingscanner.domain.dto.ListProduct
import com.example.shoppingscanner.domain.usecase.GetProductList
import com.example.shoppingscanner.presentation.ui.base.BaseEvent
import com.example.shoppingscanner.presentation.ui.base.BaseViewModel
import com.example.shoppingscanner.presentation.ui.shared.SharedViewModel
import com.example.shoppingscanner.presentation.ui.shared.ShoppingListState
import com.example.shoppingscanner.util.ProductCategory
import com.example.shoppingscanner.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductListUseCase: GetProductList,
    private val sharedViewModel: SharedViewModel,
    ) : BaseViewModel<ProductListState>(ProductListState()) {

    var shoppingListState: State<ShoppingListState> = sharedViewModel.shoppingListState

    private val _baseEvent = MutableStateFlow<BaseEvent?>(null)
    val baseEvent: StateFlow<BaseEvent?> = _baseEvent

    private fun setSearchName(category: String): String {
        return when (category) {
            ProductCategory.SHOE.categoryName -> ProductCategory.SHOE.searchName
            ProductCategory.OFFICE.categoryName -> ProductCategory.OFFICE.searchName
            ProductCategory.DRESS.categoryName -> ProductCategory.DRESS.searchName
            ProductCategory.LUGGAGE.categoryName -> ProductCategory.LUGGAGE.searchName
            ProductCategory.JACKET.categoryName -> ProductCategory.JACKET.searchName
            ProductCategory.DECORATION.categoryName -> ProductCategory.DECORATION.searchName
            ProductCategory.CHAIR.categoryName -> ProductCategory.CHAIR.searchName
            else -> ProductCategory.LUGGAGE.categoryName
        }
    }

    fun getProductListFromAPI(category:String) {
        val apiCategory = setSearchName(category)
        getProductListUseCase.executeGetProductList(apiCategory).onEach {
            when(it){
                is Resource.Success -> {
                    setState {
                        copy(productList = it.data)
                    }
                }
                is Resource.Error -> {
                    setState {
                        copy(
                            error = it.message,
                        )
                    }
                }
                is Resource.Loading -> {
                    setState {
                        copy(isLoading = true)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addToList(currentProduct:ListProduct) {
        var shoppingList = shoppingListState.value.shoppingList

        val existingProduct = shoppingList.find {
            it.barcodeNumber == currentProduct?.barcodeNumber
        }
        if (existingProduct != null) {
            setState {
                copy(messageId = R.string.product_exist)
            }
        } else {
            setState {
                copy(messageId = R.string.product_added)
            }
            shoppingListState.value.shoppingList = shoppingList.plus(currentProduct)
        }
    }

    fun removeFromList(product: ListProduct) {
        val updatedShoppingList = shoppingListState.value.shoppingList.filterNot {
            it.barcodeNumber == product.barcodeNumber
        }
        shoppingListState.value.shoppingList = updatedShoppingList
        onEvent(BaseEvent.RemoveProduct)
    }

    fun onHandledEvent() {
        _baseEvent.value = null
    }

    override fun onEvent(event: BaseEvent){
        when (event){
            is BaseEvent.GetData -> {
                getProductListFromAPI(event.category)
            }
            is BaseEvent.OnHandledMessage -> {
                setState {
                    copy(messageId = null)
                }
            }
            is BaseEvent.RemoveProduct -> {
                viewModelScope.launch {
                    _baseEvent.emit(event)
                }
            }
            else -> {}
        }
    }

}