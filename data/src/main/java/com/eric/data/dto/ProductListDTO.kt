package com.eric.data.dto

data class ProductListDTO(
    val products: List<ProductDTO>,
    val pagesAvailable: Int,
    val results: Int,
)

data class ProductDTO(
    val productId: String,
    val type: String,
    val title: String,
    val code: String,
    val defaultSkuId: String,
    val outOfStock: Boolean,
    val inStoreOnly: Boolean,
    val averageRating: Double,
    val image: String,
    val alternativeImageUrls: List<String>,
    val variantPriceRange: VariantPriceRangeDTO,
)

