package com.eric.jlp_android.feature.productDetail.viewModel

import com.eric.domain.models.ProductDetailsDomainModel
import com.eric.domain.shared.ErrorEntity
import com.eric.domain.shared.ResultResponse
import com.eric.domain.usecases.ProductDetailsParams
import com.eric.domain.usecases.ProductDetailsUseCase
import com.eric.jlp_android.feature.productDetail.state.ProductDetailState
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
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class ProductDetailViewModelTest {

    private val mockProductDetailsUseCase = Mockito.mock(ProductDetailsUseCase::class.java)
    private lateinit var sut: ProductDetailViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        sut = ProductDetailViewModel(mockProductDetailsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `load details success`() {
        runBlockingTest {
            givenILoadProductDetails("productId")
            val result = whenILoadTheList("productId")
            thenIShowTheDetails(result)
        }
    }

    @Test
    fun `load details error`() {
        runBlockingTest {
            givenILoadAnError()
            val result = whenILoadTheList("productId")
            thenIShowTheError(result)
        }
    }

    private fun thenIShowTheError(result: ProductDetailState) {
        assert(result is ProductDetailState.Error)
    }

    private suspend fun givenILoadAnError() {
        whenever(mockProductDetailsUseCase.execute(ProductDetailsParams.GetProductDetails(any()))).thenReturn(
            flowOf(ResultResponse.Failure(ErrorEntity.Unknown("test error")))
        )
    }

    private fun thenIShowTheDetails(result: ProductDetailState) {
        val state = result as ProductDetailState.ShowDetails
        assert(state.productDetailDomainModel.title == "title")
        assert(state.productDetailDomainModel.code == "title")
        assert(state.productDetailDomainModel.description == "description")
        assert(state.productDetailDomainModel.price == "price")
    }

    private fun whenILoadTheList(id: String): ProductDetailState {
        sut.loadDetails(id)
        return sut.stateProductDetail.value
    }

    private suspend fun givenILoadProductDetails(id: String) {
        whenever(mockProductDetailsUseCase.execute(ProductDetailsParams.GetProductDetails(id))).thenReturn(
            flowOf(
                ResultResponse.Success(
                    ProductDetailsDomainModel(
                        title = "title",
                        code = "code",
                        description = "description",
                        price = "price",
                        imageUrls = listOf("url1", "url2")
                    )
                )
            )
        )
    }
}