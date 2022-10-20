package com.eric.jlp_android.feature.productList.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eric.domain.models.ProductDomainModel
import com.eric.domain.models.ProductListDomainModel
import com.eric.jlp_android.R
import com.eric.jlp_android.base.BaseViewHolder

class ProductListAdapter(private val listener: ProductItemClickListener) :
    RecyclerView.Adapter<BaseViewHolder<ProductDomainModel>>() {

    interface ProductItemClickListener {
        fun onProductListClicked(productId: String)
    }

    private var productList = mutableListOf<ProductDomainModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ProductDomainModel> {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            inflater.inflate(R.layout.viewholder_product_list, parent, false)
        return ProductItemViewHolder(view, listener)
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ProductDomainModel>,
        position: Int
    ) {
        holder.bind(productList[position])
    }

    fun setItems(productListDomainModel: ProductListDomainModel) {
        productList = productListDomainModel.productDomainModels.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount() = productList.size
}