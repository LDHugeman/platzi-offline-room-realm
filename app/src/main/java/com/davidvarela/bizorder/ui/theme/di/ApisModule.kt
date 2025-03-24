package com.davidvarela.bizorder.ui.theme.di

import com.davidvarela.bizorder.ui.theme.data.BizOrderApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApisModule {

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): BizOrderApi = retrofit.create(BizOrderApi::class.java)
}