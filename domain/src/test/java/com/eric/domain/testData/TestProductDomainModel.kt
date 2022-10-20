package com.eric.domain.testData

import com.eric.domain.models.ProductDetailsDomainModel
import com.eric.domain.models.ProductDomainModel

object TestProductDomainModel {

    val testProductDomainModel1 = ProductDomainModel(
        productId = "productId1",
        image = "url1",
        averageRating = 1.0,
        title = "dishwasher1",
        price = "210.00"
    )

    val testProductDomainModel2 = ProductDomainModel(
        productId = "productId2",
        image = "url2",
        averageRating = 3.0,
        title = "dishwasher2",
        price = "230.00"
    )

    val testProductDetailsDomainModel1 = ProductDetailsDomainModel(
        "title",
        "code",
        "description", "price",
        listOf(
            "url1", "url2", "url3"
        )
    )

}
