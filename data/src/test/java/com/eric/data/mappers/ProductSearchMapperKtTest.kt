package com.eric.data.mappers

import com.eric.data.dto.ProductListDTO
import com.eric.data.testData.TestProductData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductSearchMapperKtTest {

    private val pagesAvailable = 12
    private val results = 75
    private val listDTO = ProductListDTO(
        listOf(
            TestProductData.testProductDTO1, TestProductData.testProductDTO2
        ), pagesAvailable, 75
    )

    @Test
    fun mapToDomain() {
        val result = listDTO.mapToDomain()

        assert(result.pagesAvailable == pagesAvailable)
        assert(result.results == results)
        assert(result.productDomainModels.size == 2)

        val model1 = result.productDomainModels[0]
        assert(model1.productId == "productId")
        assert(model1.averageRating == 3.4)
        assert(model1.image == "mainImageUrl")

        val model2 = result.productDomainModels[1]
        assert(model2.productId == "productId2")
        assert(model2.averageRating == 2.1)
        assert(model2.image == "mainImageUrl2")
    }
}