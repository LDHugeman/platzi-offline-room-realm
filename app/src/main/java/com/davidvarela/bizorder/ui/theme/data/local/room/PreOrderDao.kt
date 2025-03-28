package com.davidvarela.bizorder.ui.theme.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PreOrderDao {

    @Insert
    suspend fun insertPreOrder(preOrderEntity: PreOrderEntity)

    @Query("SELECT * FROM pre_orders")
    fun getPreOrders(): Flow<List<PreOrderEntity>>

    @Query("DELETE FROM pre_orders WHERE id = :id")
    suspend fun deletePreOrder(id: String)

    @Query("UPDATE pre_orders SET isSent = :isSent WHERE id = :id")
    suspend fun updatePreOrder(id: Long, isSent: Boolean)
}