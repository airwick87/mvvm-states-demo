package com.eric.domain.usecases

import com.eric.domain.models.ProductListDomainModel
import com.eric.domain.repositories.ProductRepository
import com.eric.domain.shared.ResultResponse
import com.eric.domain.testData.TestProductDomainModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ProductListUseCaseTest {
    private val mockProductRepository = Mockito.mock(ProductRepository::class.java)

    private lateinit var sut: ProductListUseCase

    @Before
    fun setup() {
        sut = ProductListUseCase(mockProductRepository)
    }

    @Test
    fun `get product list`() {
        runBlockingTest {
            givenISearchProductList("dishwashers")
            whenIExecuteSearchUseCase("dishwashers") {
                thenReceiveDetails(it)
            }
        }
    }

    private fun thenReceiveDetails(resultResponse: ResultResponse<ProductListDomainModel>) {
        assert(resultResponse.isSuccess())
        val result = resultResponse.extractData()
        assert(
            result.productDomainModels[0] == TestProductDomainModel.testProductDomainModel1
        )
        assert(
            result.productDomainModels[1] == TestProductDomainModel.testProductDomainModel2
        )
        assert(result.results == 2)
        assert(result.pagesAvailable == 1)
    }

    private suspend fun whenIExecuteSearchUseCase(
        searchTerm: String,
        func: (ResultResponse<ProductListDomainModel>) -> Unit
    ) {
        sut.execute(ProductListParams.GetProductListByTerm(searchTerm)).collect {
            func.invoke(it)
        }
    }

    private suspend fun givenISearchProductList(searchTerm: String) {
        whenever(mockProductRepository.getProducts(searchTerm)).thenReturn(
            flowOf(
                ResultResponse.Success(
                    ProductListDomainModel(
                        listOf(
                            TestProductDomainModel.testProductDomainModel1,
                            TestProductDomainModel.testProductDomainModel2
                        ),
                        2, 1
                    )
                )
            )
        )
    }
}