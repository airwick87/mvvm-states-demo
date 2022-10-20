package com.eric.data.mappers

import com.eric.data.dto.ProductDetailDTO
import com.eric.domain.models.ProductDetailsDomainModel

fun ProductDetailDTO.mapToDomain(): ProductDetailsDomainModel {
    return ProductDetailsDomainModel(
        title = title,
        code = code,
        price = price.now,
        description = details.productInformation,
        imageUrls = media.images.urls,
    )
}