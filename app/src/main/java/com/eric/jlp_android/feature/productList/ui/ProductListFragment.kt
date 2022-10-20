package com.eric.jlp_android.feature.productList.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.eric.jlp_android.R
import com.eric.jlp_android.base.BaseFragment
import com.eric.jlp_android.base.RouteConstants
import com.eric.jlp_android.databinding.FragmentProductlistBinding
import com.eric.jlp_android.feature.productList.state.ProductListState
import com.eric.jlp_android.feature.productList.viewModel.ProductListViewModel
import com.eric.jlp_android.util.hide
import com.eric.jlp_android.util.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment :
    BaseFragment<FragmentProductlistBinding>(FragmentProductlistBinding::inflate),
    ProductListAdapter.ProductItemClickListener {

    private val viewModel: ProductListViewModel by viewModels()
    private val adapter = ProductListAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.productListRv.adapter = adapter
        viewModel.loadList()
    }

    override fun bindObservers() {
        lifecycleScope.launch {
            viewModel.stateProductList.collect { state ->
                when (state) {
                    is ProductListState.SetTitle -> binding.titleTv.text = state.title
                    is ProductListState.Error -> showError(state.message)
                    ProductListState.Idle -> {}
                    ProductListState.Loading -> {
                        binding.productListRv.hide()
                        binding.loadingSpinner.show()
                    }
                    is ProductListState.ShowList -> {
                        adapter.setItems(state.productListDomainModel)
                        binding.productListRv.show()
                        binding.loadingSpinner.hide()
                    }
                }
            }
        }
    }

    override fun onProductListClicked(productId: String) {
        findNavController().navigate(
            R.id.action_ProductListFragment_to_ProductDetailFragment,
            bundleOf(RouteConstants.productIdKey to productId)
        )
    }
}