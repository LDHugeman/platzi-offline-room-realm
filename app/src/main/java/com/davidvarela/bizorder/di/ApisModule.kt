package com.davidvarela.bizorder.di

import com.davidvarela.bizorder.data.remote.BizOrderApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApisModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): BizOrderApi = retrofit.create(BizOrderApi::class.java)
}