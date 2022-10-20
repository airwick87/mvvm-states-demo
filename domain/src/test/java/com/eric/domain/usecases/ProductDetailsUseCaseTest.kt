package com.eric.domain.usecases

import com.eric.domain.models.ProductDetailsDomainModel
import com.eric.domain.repositories.ProductRepository
import com.eric.domain.shared.ResultResponse
import com.eric.domain.testData.TestProductDomainModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class ProductDetailsUseCaseTest {

    private val mockProductRepository = mock(ProductRepository::class.java)

    private lateinit var sut: ProductDetailsUseCase

    @Before
    fun setup() {
        sut = ProductDetailsUseCase(mockProductRepository)
    }

    @Test
    fun `get product details`() {
        runBlockingTest {
            givenIGetProductDetails("productId")
            whenIExecuteDetailsUseCase("productId") {
                thenReceiveDetails(it)
            }
        }
    }

    private fun thenReceiveDetails(resultResponse: ResultResponse<ProductDetailsDomainModel>) {
        assert(resultResponse.isSuccess())
        val result = resultResponse.extractData()
        assert(result.title == "title")
        assert(result.code == "code")
        assert(result.imageUrls == listOf("url1", "url2", "url3"))

    }

    private suspend fun whenIExecuteDetailsUseCase(
        id: String,
        func: (ResultResponse<ProductDetailsDomainModel>) -> Unit
    ) {
        sut.execute(ProductDetailsParams.GetProductDetails(id)).collect {
            func.invoke(it)
        }
    }

    private suspend fun givenIGetProductDetails(id: String) {
        whenever(mockProductRepository.getProductDetail(id)).thenReturn(
            flowOf(
                ResultResponse.Success(
                    TestProductDomainModel.testProductDetailsDomainModel1
                )
            )
        )
    }
}