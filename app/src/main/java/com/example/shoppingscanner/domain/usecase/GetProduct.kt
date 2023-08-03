package com.example.shoppingscanner.domain.usecase

import com.example.shoppingscanner.data.remote.mapper.toDomainModel
import com.example.shoppingscanner.domain.dto.CartProduct
import com.example.shoppingscanner.domain.repository.ProductRepository
import com.example.shoppingscanner.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProduct @Inject constructor(
    private val repository:ProductRepository
) {
    fun executeGetProduct(barcode:String): Flow<Resource<List<CartProduct>>> = flow {
        try{
            emit(Resource.Loading())
            val productList = repository.getProductDetails(barcode)
            if(productList.products != null){
                emit(Resource.Success(productList.toDomainModel()))
            }else{
                emit(Resource.Error("Product not found."))
            }
        }catch(e:Exception){
            emit(Resource.Error(e.localizedMessage))
        }
    }
}