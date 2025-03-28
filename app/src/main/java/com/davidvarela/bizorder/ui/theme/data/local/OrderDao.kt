package com.davidvarela.bizorder.ui.theme.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {

    @Upsert
    suspend fun insertOder(orderEntity: List<OrderEntity>)

    @Query("SELECT * FROM orders")
    fun getOrders(): Flow<List<OrderEntity>>

    @Query("SELECT * FROM orders WHERE id = :id")
    suspend fun getOrder(id: String): OrderEntity
}