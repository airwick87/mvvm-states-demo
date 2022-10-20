package com.eric.data.services

import com.eric.data.dto.ProductListDTO
import com.eric.domain.models.ProductListDomainModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductSearchService {
    @GET("/search/api/rest/v2/catalog/products/search/keyword")
    suspend fun getProducts(
        @Query("q") searchTerm: String
    ): Response<ProductListDTO>
}