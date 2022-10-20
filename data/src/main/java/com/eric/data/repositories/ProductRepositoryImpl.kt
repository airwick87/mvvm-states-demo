package com.eric.data.repositories

import com.eric.data.mappers.mapToDomain
import com.eric.data.services.ProductDetailService
import com.eric.data.services.ProductSearchService
import com.eric.domain.models.ProductDetailsDomainModel
import com.eric.domain.models.ProductListDomainModel
import com.eric.domain.repositories.ProductRepository
import com.eric.domain.shared.ErrorEntity
import com.eric.domain.shared.ResultResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepositoryImpl(
    private val productSearchService: ProductSearchService,
    private val productDetailService: ProductDetailService
): ProductRepository {
   override suspend fun getProducts(query: String): Flow<ResultResponse<ProductListDomainModel>> =
        flowOf(productSearchService.getProducts(query).run {
            this.body()?.let {
                if (isSuccessful) {
                    ResultResponse.Success(it.mapToDomain())
                } else {
                    ResultResponse.Failure(ErrorEntity.Unknown(this.errorBody().toString()))
                }
            } ?: ResultResponse.Failure(ErrorEntity.NoResponse(this.errorBody().toString()))
        })

    override suspend fun getProductDetail(id: String): Flow<ResultResponse<ProductDetailsDomainModel>> =
        flowOf(productDetailService.getProductDetail(id).run {
            this.body()?.let {
                if (isSuccessful) {
                    ResultResponse.Success(it.mapToDomain())
                } else {
                    ResultResponse.Failure(ErrorEntity.Unknown(this.errorBody().toString()))
                }
            } ?: ResultResponse.Failure(ErrorEntity.NoResponse(this.errorBody().toString()))
        })
}