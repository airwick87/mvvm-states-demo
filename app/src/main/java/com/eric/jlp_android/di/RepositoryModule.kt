package com.eric.jlp_android.di

import com.eric.data.repositories.ProductRepositoryImpl
import com.eric.data.services.ProductDetailService
import com.eric.data.services.ProductSearchService
import com.eric.domain.repositories.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideMoviesRepository(
        productSearchService: ProductSearchService,
        productDetailService: ProductDetailService,
    ): ProductRepository =
        ProductRepositoryImpl(productSearchService, productDetailService)
}