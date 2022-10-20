package com.eric.data.services

import com.eric.data.dto.ProductDetailDTO
import com.eric.domain.models.ProductDetailsDomainModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductDetailService {
    @GET("/mobile-apps/api/v1/products/{productId}")
    suspend fun getProductDetail(@Path("productId") productId: String): Response<ProductDetailDTO>
}