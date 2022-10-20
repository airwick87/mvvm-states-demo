package com.eric.jlp_android.feature.productList.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eric.domain.usecases.ProductListParams
import com.eric.domain.usecases.ProductListUseCase
import com.eric.jlp_android.feature.productList.state.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productListUseCase: ProductListUseCase
) : ViewModel() {

    private val defaultSearchTerm = "Dishwashers"
    private val productList = MutableStateFlow<ProductListState>(ProductListState.Idle)
    val stateProductList: StateFlow<ProductListState> get() = productList

    fun loadList(searchTerm: String = defaultSearchTerm) {
        productList.value = ProductListState.SetTitle(searchTerm)
        viewModelScope.launch {
            productList.value = ProductListState.Loading
            productListUseCase.execute(ProductListParams.GetProductListByTerm(searchTerm)).collect {
                productList.value = when {
                    it.isSuccess() -> {
                        ProductListState.ShowList(it.extractData())
                    }
                    it.isFailure() -> {
                        ProductListState.Error(it.extractError().message)
                    }
                    else -> {
                        ProductListState.Idle
                    }
                }
            }
        }
    }
}