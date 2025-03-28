package com.davidvarela.bizorder.ui.theme.data.local

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    fun provideDatabase(application: Application): AppDataBase {
        return Room.databaseBuilder(application, AppDataBase::class.java, "room_database.db")
            .build()
    }

    fun provideOrderDao(
        appDataBase: AppDataBase
    ): OrderDao {
        return appDataBase.orderDao()
    }

    fun providePreOrderDao(
        appDataBase: AppDataBase
    ): PreOrderDao {
        return appDataBase.preOrderDao()
    }
}