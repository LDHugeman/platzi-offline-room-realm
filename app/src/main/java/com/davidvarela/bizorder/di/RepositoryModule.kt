package com.davidvarela.bizorder.di

import com.davidvarela.bizorder.data.LocalDataStorage
import com.davidvarela.bizorder.data.OrderRepositoryImpl
import com.davidvarela.bizorder.data.PreOrdersRepositoryImpl
import com.davidvarela.bizorder.data.remote.RemoteDataStorage
import com.davidvarela.bizorder.domain.OrderRepository
import com.davidvarela.bizorder.domain.PreOrderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideOrderRepository(
        remoteDataStorage: RemoteDataStorage,
        localDataStorage: LocalDataStorage
    ): OrderRepository {
        return OrderRepositoryImpl(
            remoteDataStorage = remoteDataStorage,
            localDataStorage = localDataStorage
        )
    }

    @Provides
    @Singleton
    fun providePreOrderRepository(
        remoteDataStorage: RemoteDataStorage,
        localDataStorage: LocalDataStorage
    ): PreOrderRepository {
        return PreOrdersRepositoryImpl(
            remoteDataStorage = remoteDataStorage,
            localDataStorage = localDataStorage
        )
    }
}