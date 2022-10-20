package com.eric.data.mappers

import com.eric.data.dto.DetailPriceDTO
import com.eric.data.dto.DetailsDTO
import com.eric.data.dto.ImageListDTO
import com.eric.data.dto.MediaDTO
import com.eric.data.dto.ProductDetailDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductDetailMapperKtTest {

    private val detailDTO = ProductDetailDTO(
        title = "title",
        code = "code",
        media = MediaDTO(images = ImageListDTO(listOf("url1", "url2", "url3"))),
        details = DetailsDTO(productInformation = "productInformation"),
        price = DetailPriceDTO("230.00"),
    )

    @Test
    fun mapToDomain() {
        val result = detailDTO.mapToDomain()

        assert(result.imageUrls == listOf("url1", "url2", "url3"))
        assert(result.code == "code")
        assert(result.price == "230.00")
        assert(result.title == "title")
        assert(result.description == "productInformation")

    }
}