package com.eric.data.mappers

import com.eric.data.dto.ProductDTO
import com.eric.data.dto.ProductListDTO
import com.eric.domain.models.ProductDomainModel
import com.eric.domain.models.ProductListDomainModel

fun ProductListDTO.mapToDomain(): ProductListDomainModel =
    ProductListDomainModel(products.mapToDomain(), results, pagesAvailable)

fun List<ProductDTO>.mapToDomain(): List<ProductDomainModel> =
    map {
        ProductDomainModel(
            productId = it.productId,
            image = it.image,
            averageRating = it.averageRating,
            title = it.title,
            price = "£" + it.variantPriceRange.value.max
        )
    }

