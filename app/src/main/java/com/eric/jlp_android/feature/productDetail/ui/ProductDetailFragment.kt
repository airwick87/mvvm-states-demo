package com.eric.jlp_android.feature.productDetail.ui

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.eric.jlp_android.base.BaseFragment
import com.eric.jlp_android.base.RouteConstants
import com.eric.jlp_android.databinding.FragmentProductdetailBinding
import com.eric.jlp_android.feature.productDetail.state.ProductDetailState
import com.eric.jlp_android.feature.productDetail.viewModel.ProductDetailViewModel
import com.eric.jlp_android.util.hide
import com.eric.jlp_android.util.load
import com.eric.jlp_android.util.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductDetailFragment :
    BaseFragment<FragmentProductdetailBinding>(FragmentProductdetailBinding::inflate) {

    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val productId = arguments?.getString(RouteConstants.productIdKey)
        productId?.let {
            viewModel.loadDetails(it)
        }
    }

    override fun bindObservers() {
        lifecycleScope.launch {
            viewModel.stateProductDetail.collect { state ->
                when (state) {
                    is ProductDetailState.Error -> showError(state.message)
                    ProductDetailState.Idle -> {}
                    ProductDetailState.Loading -> binding.loadingSpinner.show()
                    is ProductDetailState.ShowDetails -> {
                        binding.loadingSpinner.hide()
                        state.productDetailDomainModel.apply {
                            binding.image.load(imageUrls.first())
                            binding.title.text = title
                            binding.code.text = code
                            binding.price.text = price
                            binding.description.text = Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT)
                        }

                    }
                }
            }
        }
    }

}