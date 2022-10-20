package com.eric.jlp_android.feature.productDetail.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eric.domain.usecases.ProductDetailsParams
import com.eric.domain.usecases.ProductDetailsUseCase
import com.eric.jlp_android.feature.productDetail.state.ProductDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productDetailsUseCase: ProductDetailsUseCase
) : ViewModel() {

    private val productDetail = MutableStateFlow<ProductDetailState>(ProductDetailState.Idle)
    val stateProductDetail: StateFlow<ProductDetailState> get() = productDetail

    fun loadDetails(id: String) {
        viewModelScope.launch {
            productDetail.value = ProductDetailState.Loading
            productDetailsUseCase.execute(ProductDetailsParams.GetProductDetails(id)).collect {
                productDetail.value = when {
                    it.isSuccess() -> {
                        ProductDetailState.ShowDetails(it.extractData())
                    }
                    it.isFailure() -> {
                        ProductDetailState.Error(it.extractError().message)
                    }
                    else -> {
                        ProductDetailState.Idle
                    }
                }
            }
        }
    }
}