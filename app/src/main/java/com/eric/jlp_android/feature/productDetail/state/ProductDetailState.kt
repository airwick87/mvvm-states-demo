package com.eric.jlp_android.feature.productDetail.state

import com.eric.domain.models.ProductDetailsDomainModel

sealed class ProductDetailState {
    object Idle : ProductDetailState()
    object Loading : ProductDetailState()
    data class ShowDetails(val productDetailDomainModel: ProductDetailsDomainModel) : ProductDetailState()
    data class Error(val message: String) : ProductDetailState()
}