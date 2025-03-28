package com.davidvarela.bizorder.data.local.room

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): AppDataBase {
        return Room.databaseBuilder(application, AppDataBase::class.java, "room_database.db")
            .build()
    }

    @Provides
    fun provideOrderDao(
        appDataBase: AppDataBase
    ): OrderDao {
        return appDataBase.orderDao()
    }

    @Provides
    fun providePreOrderDao(
        appDataBase: AppDataBase
    ): PreOrderDao {
        return appDataBase.preOrderDao()
    }
}