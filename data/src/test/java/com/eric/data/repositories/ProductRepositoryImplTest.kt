package com.eric.data.repositories

import com.eric.data.dto.ProductListDTO
import com.eric.data.services.ProductDetailService
import com.eric.data.services.ProductSearchService
import com.eric.data.testData.TestProductData
import com.eric.domain.models.ProductDetailsDomainModel
import com.eric.domain.models.ProductListDomainModel
import com.eric.domain.shared.ResultResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.then
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
class ProductRepositoryImplTest {

    private val mockProductDetailService = mock(ProductDetailService::class.java)
    private val mockProductSearchService = mock(ProductSearchService::class.java)

    private lateinit var sut: ProductRepositoryImpl

    private val productId = "asdjkl123123"
    private val searchTerm = "dishwasher"

    @Before
    fun setup() {
        sut = ProductRepositoryImpl(mockProductSearchService, mockProductDetailService)
    }

    @Test
    fun `get product detail success`() {
        runBlockingTest {
            givenIGetProductDetailSuccess(productId)
            whenIGetProductDetails {
                thenIHaveTheDetails(it)
            }
            verifyProductDetailIsCalled(productId)
        }
    }

    @Test
    fun `get product Search success`() {
        runBlockingTest {
            givenIGetProductSearchSuccess(searchTerm)
            whenIGetProductSearchResults {
                thenIHaveTheSearchResults(it)
            }
            verifyProductSearchIsCalled(searchTerm)
        }
    }

    private fun thenIHaveTheDetails(result: ResultResponse<ProductDetailsDomainModel>) {
        assert(result.isSuccess())
        val data = result.extractData()
        assert(data.code == TestProductData.testProductDetail1.code)
        assert(data.title == TestProductData.testProductDetail1.title)
        assert(data.imageUrls == TestProductData.testProductDetail1.media.images.urls)
    }

    private fun thenIHaveTheSearchResults(result: ResultResponse<ProductListDomainModel>) {
        assert(result.isSuccess())
        val data = result.extractData()
        assert(data.pagesAvailable == 1)
        assert(data.results == 2)

        assert(data.productDomainModels.first().title == TestProductData.testProductDTO1.title)
        assert(data.productDomainModels.first().productId == TestProductData.testProductDTO1.productId)
    }


    private suspend fun whenIGetProductDetails(func: (ResultResponse<ProductDetailsDomainModel>) -> Unit) {
        sut.getProductDetail(productId).collect {
            func.invoke(it)
        }
    }

    private suspend fun whenIGetProductSearchResults(func: (ResultResponse<ProductListDomainModel>) -> Unit) {
        sut.getProducts(searchTerm).collect {
            func.invoke(it)
        }
    }

    private suspend fun verifyProductDetailIsCalled(id: String) {
        then(mockProductDetailService).should().getProductDetail(id)
    }

    private suspend fun verifyProductSearchIsCalled(searchTerm: String) {
        then(mockProductSearchService).should().getProducts(searchTerm)
    }

    private suspend fun givenIGetProductDetailSuccess(id: String) {
        whenever(mockProductDetailService.getProductDetail(id)).thenReturn(
            Response.success(TestProductData.testProductDetail1)
        )
    }

    private suspend fun givenIGetProductSearchSuccess(searchTerm: String) {
        whenever(mockProductSearchService.getProducts(searchTerm)).thenReturn(
            Response.success(
                ProductListDTO(
                    listOf(TestProductData.testProductDTO1, TestProductData.testProductDTO2),
                    1,
                    2
                )
            )
        )
    }
}