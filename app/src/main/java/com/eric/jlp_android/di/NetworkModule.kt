package com.eric.jlp_android.di

import com.eric.data.services.ProductDetailService
import com.eric.data.services.ProductSearchService
import com.eric.jlp_android.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private fun getLogger() =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder().apply {
            addInterceptor(NonAuthInterceptor())
            if (BuildConfig.DEBUG) {
                addInterceptor(getLogger())
            }
        }
        return builder.build()
    }

    @Provides
    @Singleton
    @AuthorisedClient
    fun provideOkHttpClientAuth(): OkHttpClient {
        val builder = OkHttpClient.Builder().apply {
            addInterceptor(AuthInterceptor())
            if (BuildConfig.DEBUG) {
                addInterceptor(getLogger())
            }
        }
        return builder.build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    @AuthorisedAdapter
    fun provideRetrofitAuth(gson: Gson, @AuthorisedClient okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideProductSearchService(@AuthorisedAdapter retrofit: Retrofit): ProductSearchService =
        retrofit.create(ProductSearchService::class.java)

    @Provides
    fun provideProductDetailService(retrofit: Retrofit): ProductDetailService =
        retrofit.create(ProductDetailService::class.java)
}

@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class AuthorisedClient

@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class AuthorisedAdapter