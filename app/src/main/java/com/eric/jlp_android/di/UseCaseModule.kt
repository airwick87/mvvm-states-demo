package com.eric.jlp_android.di

import com.eric.domain.repositories.ProductRepository
import com.eric.domain.usecases.ProductDetailsUseCase
import com.eric.domain.usecases.ProductListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideProductListUseCase(productRepository: ProductRepository) =
        ProductListUseCase(productRepository)

    @Provides
    fun provideProductDetailsUseCase(productRepository: ProductRepository) =
        ProductDetailsUseCase(productRepository)
}