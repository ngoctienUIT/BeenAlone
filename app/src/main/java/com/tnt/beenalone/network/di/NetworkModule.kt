package com.tnt.beenalone.network.di

import com.tnt.beenalone.network.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    private val retrofit = RetrofitModule.provideRetrofit()

    @Singleton
    @Provides
    fun provideUserApi(): UserApi = retrofit.create(UserApi::class.java)
}