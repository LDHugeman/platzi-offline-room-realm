package com.davidvarela.bizorder.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [OrderEntity::class, PreOrderEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun orderDao(): OrderDao
    abstract fun preOrderDao(): PreOrderDao
}