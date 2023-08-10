package com.example.shoppingscanner.domain.usecase

import com.example.shoppingscanner.data.remote.mapper.toCartProduct
import com.example.shoppingscanner.data.remote.mapper.toListProduct
import com.example.shoppingscanner.domain.dto.CartProduct
import com.example.shoppingscanner.domain.dto.ListProduct
import com.example.shoppingscanner.domain.repository.ProductRepository
import com.example.shoppingscanner.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductList @Inject constructor(
    private val repository: ProductRepository
) {
    fun executeGetProductList(category:String): Flow<Resource<List<ListProduct>>> = flow {
        try{
            emit(Resource.Loading())
            val productList = repository.getProductList(category)
            if(productList.products != null){
                emit(Resource.Success(productList.toListProduct()))
            }else{
                emit(Resource.Error("Product not found."))
            }
        }catch(e:Exception){
            emit(Resource.Error(e.localizedMessage))
        }
    }
}