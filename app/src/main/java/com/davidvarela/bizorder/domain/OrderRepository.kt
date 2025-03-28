package com.davidvarela.bizorder.domain

import kotlinx.coroutines.flow.Flow

interface OrderRepository {

    fun getOrders(): Flow<Result<List<Order>>>

    suspend fun getOrderById(id: String): Result<Order?>
}