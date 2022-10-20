package com.eric.domain.usecases

import com.eric.domain.models.ProductDetailsDomainModel
import com.eric.domain.repositories.ProductRepository
import com.eric.domain.shared.ResultResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductDetailsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : GeneralUseCase<ProductDetailsParams, ProductDetailsDomainModel>() {

    override suspend fun buildFlow(param: ProductDetailsParams): Flow<ResultResponse<ProductDetailsDomainModel>> =
        when (param) {
            is ProductDetailsParams.GetProductDetails -> productRepository.getProductDetail(param.id)
        }
}

sealed class ProductDetailsParams : RequestBodyParam() {
    data class GetProductDetails(val id: String) : ProductDetailsParams()
}