package com.example.shoppingscanner.presentation.ui.productlist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductListUseCase: GetProductList,
    private val sharedViewModel: SharedViewModel,
    ) : BaseViewModel<ProductListState>(ProductListState()) {

    var shoppingListState: State<ShoppingListState> = sharedViewModel.shoppingListState

    private fun setSearchName(category: String): String {
        return when (category) {
            ProductCategory.SHOE.categoryName -> ProductCategory.SHOE.searchName
            ProductCategory.LUGGAGE.categoryName -> ProductCategory.LUGGAGE.searchName
            ProductCategory.JACKET.categoryName -> ProductCategory.JACKET.searchName
            ProductCategory.DECORATION.categoryName -> ProductCategory.DECORATION.searchName
            ProductCategory.CHAIR.categoryName -> ProductCategory.CHAIR.searchName
            else ->   ProductCategory.SHOE.categoryName
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
                            messageId = R.string.try_again
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
            it.barcode_number == currentProduct?.barcode_number
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

    override fun onEvent(event: BaseEvent){
        when (event){
            is BaseEvent.GetData -> {
                getProductListFromAPI(event.category)
            }
            is BaseEvent.OnHandledMessage -> {
                setState {
                    copy(messageId = null)
                }
            }else -> {}
        }
    }

}