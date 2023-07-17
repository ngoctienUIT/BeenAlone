package com.tnt.beenalone.network.di

import com.tnt.beenalone.core.Constants.BASE_URL_V1
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object RetrofitModule {
    @Singleton
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient
            .Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        client.networkInterceptors().add(Interceptor { chain ->
            val requestBuilder: Request.Builder = chain.request().newBuilder()
            requestBuilder.header("Content-Type", "application/json")
            chain.proceed(requestBuilder.build())
        })
        return Retrofit.Builder()
            .baseUrl(BASE_URL_V1)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build();
    }
}