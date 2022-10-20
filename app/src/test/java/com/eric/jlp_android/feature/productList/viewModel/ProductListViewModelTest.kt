package com.eric.jlp_android.feature.productList.viewModel

import com.eric.domain.models.ProductListDomainModel
import com.eric.domain.shared.ResultResponse
import com.eric.domain.usecases.ProductListParams
import com.eric.domain.usecases.ProductListUseCase
import com.eric.jlp_android.feature.productList.state.ProductListState
import com.eric.jlp_android.testdata.TestProductDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ProductListViewModelTest {

    private val mockProductListUseCase = mock(ProductListUseCase::class.java)
    private val searchTerm = "dishwasher"
    private lateinit var sut: ProductListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        sut = ProductListViewModel(mockProductListUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `load list success`() {
        runBlockingTest {
            givenILoadAProductList(searchTerm)
            val result = whenILoadTheList(searchTerm)
            thenIShowTheResults(result)
        }
    }

    private fun thenIShowTheResults(result: ProductListState) {
        val state = result as ProductListState.ShowList
        assert(state.productListDomainModel.results == 2)
        assert(state.productListDomainModel.productDomainModels[0] == TestProductDomainModel.testProductDomainModel1)
        assert(state.productListDomainModel.productDomainModels[1] == TestProductDomainModel.testProductDomainModel2)
    }

    private fun whenILoadTheList(searchTerm: String): ProductListState {
        sut.loadList(searchTerm)
        return sut.stateProductList.value
    }

    private suspend fun givenILoadAProductList(searchTerm: String) {
        whenever(mockProductListUseCase.execute(ProductListParams.GetProductListByTerm(searchTerm))).thenReturn(
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