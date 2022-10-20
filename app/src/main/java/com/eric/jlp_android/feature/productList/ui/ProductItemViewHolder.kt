package com.eric.jlp_android.feature.productList.ui

import android.view.View
import com.eric.domain.models.ProductDomainModel
import com.eric.jlp_android.databinding.ViewholderProductListBinding
import com.eric.jlp_android.base.BaseViewHolder
import com.eric.jlp_android.util.load

class ProductItemViewHolder(
    itemView: View,
    private val listener: ProductListAdapter.ProductItemClickListener
) : BaseViewHolder<ProductDomainModel>(itemView) {
    override fun bind(data: ProductDomainModel) {
        val binding = ViewholderProductListBinding.bind(itemView)
        binding.apply {
            root.setOnClickListener {
                listener.onProductListClicked(data.productId)
            }
            title.text = data.title
            price.text = data.price
            image.load(data.image)
        }
    }
}