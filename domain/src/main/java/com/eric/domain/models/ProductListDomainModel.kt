package com.eric.domain.models

data class ProductListDomainModel(
    val productDomainModels: List<ProductDomainModel>,
    val results: Int,
    val pagesAvailable: Int
)

data class ProductDomainModel(
    val productId: String,
    val image: String,
    val averageRating: Double,
    val title: String,
    val price: String,
)


