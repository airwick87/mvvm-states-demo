package com.eric.data.testData

import com.eric.data.dto.DetailPriceDTO
import com.eric.data.dto.DetailsDTO
import com.eric.data.dto.ImageListDTO
import com.eric.data.dto.MediaDTO
import com.eric.data.dto.ProductDTO
import com.eric.data.dto.ProductDetailDTO
import com.eric.data.dto.ValueRangeDTO
import com.eric.data.dto.VariantPriceRangeDTO

object TestProductData {
    val testProductDTO1 = ProductDTO(
        "productId",
        type = "standard",
        title = "title",
        code = "code",
        defaultSkuId = "defaultSkuId",
        outOfStock = false,
        inStoreOnly = true,
        averageRating = 3.4,
        image = "mainImageUrl",
        alternativeImageUrls = listOf("url1", "url2", "url3"),
        variantPriceRange = VariantPriceRangeDTO(ValueRangeDTO("420.00", "750.00"))
    )
    val testProductDTO2 = ProductDTO(
        "productId2",
        type = "standard",
        title = "title2",
        code = "code2",
        defaultSkuId = "defaultSkuId2",
        outOfStock = true,
        inStoreOnly = false,
        averageRating = 2.1,
        image = "mainImageUrl2",
        alternativeImageUrls = listOf("url1", "url2", "url3"),
        variantPriceRange = VariantPriceRangeDTO(ValueRangeDTO("120.00", "150.00"))
    )

    val testProductDetail1 = ProductDetailDTO(
        title = "title",
        code = "code",
        details = DetailsDTO("details"),
        price = DetailPriceDTO("422.00"),
        media = MediaDTO(ImageListDTO(listOf("url1", "url2", "url3")))
    )
}


