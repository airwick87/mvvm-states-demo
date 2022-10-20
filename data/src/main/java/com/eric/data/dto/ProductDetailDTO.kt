package com.eric.data.dto

data class ProductDetailDTO(
    val title: String,
    val code: String,
    val media: MediaDTO,
    val details: DetailsDTO,
    val price: DetailPriceDTO,
)


