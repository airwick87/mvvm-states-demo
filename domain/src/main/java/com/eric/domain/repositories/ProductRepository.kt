package com.eric.domain.repositories

import com.eric.domain.models.ProductDetailsDomainModel
import com.eric.domain.models.ProductListDomainModel
import com.eric.domain.shared.ResultResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(query: String): Flow<ResultResponse<ProductListDomainModel>>
    suspend fun getProductDetail(id: String): Flow<ResultResponse<ProductDetailsDomainModel>>
}