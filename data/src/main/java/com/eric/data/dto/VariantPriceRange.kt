package com.eric.data.dto

data class VariantPriceRangeDTO(
    val value: ValueRangeDTO
)

data class ValueRangeDTO(
    val min: String,
    val max: String,
)