package com.eric.domain.usecases

import com.eric.domain.models.ProductListDomainModel
import com.eric.domain.repositories.ProductRepository
import com.eric.domain.shared.ResultResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductListUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : GeneralUseCase<ProductListParams, ProductListDomainModel>() {

    override suspend fun buildFlow(param: ProductListParams): Flow<ResultResponse<ProductListDomainModel>> =
        when (param) {
            is ProductListParams.GetProductListByTerm -> productRepository.getProducts(
                param.searchTerm
            )
        }
}

sealed class ProductListParams : RequestBodyParam() {
    data class GetProductListByTerm(val searchTerm: String) :
        ProductListParams()
}