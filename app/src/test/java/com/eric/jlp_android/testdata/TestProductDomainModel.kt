package com.eric.jlp_android.testdata

import com.eric.domain.models.ProductDomainModel

object TestProductDomainModel {

    val testProductDomainModel1 = ProductDomainModel(
        productId = "productId1",
        image = "url1",
        averageRating = 1.0,
        title = "dishwasher1",
        price = "543.44"
    )

    val testProductDomainModel2 = ProductDomainModel(
        productId = "productId2",
        image = "url2",
        averageRating = 3.0,
        title = "dishwasher2",
        price = "423.44"
    )
}
