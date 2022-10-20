package com.eric.jlp_android.feature.productList.state

import com.eric.domain.models.ProductListDomainModel

sealed class ProductListState {
    object Idle : ProductListState()
    object Loading : ProductListState()
    data class SetTitle(val title: String) : ProductListState()
    data class ShowList(val productListDomainModel: ProductListDomainModel) : ProductListState()
    data class Error(val message: String) : ProductListState()
}