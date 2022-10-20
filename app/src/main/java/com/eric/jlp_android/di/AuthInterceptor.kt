package com.eric.jlp_android.di

import com.eric.jlp_android.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    private val apiKey = "api_key"
    private val userAgent = "User-Agent"

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder =
            chain.request().url
                .newBuilder()
                .addQueryParameter(apiKey, BuildConfig.AUTH_KEY)
                .build()
        val request =
            chain.request().newBuilder().url(builder)
                .addHeader(userAgent, "App")
                .build()
        return chain.proceed(request)
    }
}
