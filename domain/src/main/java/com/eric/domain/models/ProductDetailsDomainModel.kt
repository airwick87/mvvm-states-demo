package com.eric.domain.models

data class ProductDetailsDomainModel(
    val title: String,
    val code: String,
    val description: String,
    val price: String,
    val imageUrls: List<String> = emptyList()
)