package com.davidvarela.bizorder.domain

import kotlinx.coroutines.flow.Flow

interface PreOrderRepository {

    suspend fun savePreOrder(preOrder: PreOrder): Result<Unit>

    fun getPreOrders(): Flow<Result<List<PreOrder>>>

    suspend fun deletePreOrder(id: Long)

    suspend fun retrySync(id:Long)
}