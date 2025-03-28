package com.davidvarela.bizorder.data

import com.davidvarela.bizorder.data.local.realm.OrderObject
import com.davidvarela.bizorder.data.local.realm.OrderRealm
import com.davidvarela.bizorder.data.local.realm.PreOrderObject
import com.davidvarela.bizorder.data.local.realm.PreOrderRealm
import com.davidvarela.bizorder.data.local.room.OrderDao
import com.davidvarela.bizorder.data.local.room.OrderEntity
import com.davidvarela.bizorder.data.local.room.PreOrderDao
import com.davidvarela.bizorder.data.local.room.PreOrderEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataStorage @Inject constructor(
    private val orderDao: OrderDao,
    private val preOrderDao: PreOrderDao,
    private val orderRealm: OrderRealm,
    private val preoOrderRealm: PreOrderRealm
){

    // orders ROOM

    suspend fun upsertOrdersRoom(orders: List<OrderEntity>) = orderDao.insertOrders(orders)

    fun getOrdersRoom() = orderDao.getOrders()

    suspend fun getOrderByOrderIdRoom(id: String) = runCatching {
        orderDao.getOrderById(id)
    }

    // orders REALM

    suspend fun insertOrdersRealm(orders: List<OrderObject>) = orderRealm.insertOrders(orders)

    fun getOrdersRealm() = orderRealm.getOrders()

    suspend fun getOrderByOrderIdRealm(id: String): Result<OrderObject?> = runCatching {
        orderRealm.getOrderById(id)
    }


    // preOrders ROOM

    suspend fun savePreOrderRoom(preOrder: PreOrderEntity) = preOrderDao.insertPreOrder(preOrder)

    fun getPreOrdersRoom(): Flow<List<PreOrderEntity>> = preOrderDao.getPreOrders()

    suspend fun deletePreOrderIdRoom(id: Long) = preOrderDao.deletePreOrder(id)

    suspend fun  retrySyncRoom(id: Long, isSent: Boolean) = preOrderDao.updatePreOrder(id, isSent)

    // preOrders REALM

    suspend fun savePreOrderRealm(preOrder: PreOrderObject) = preoOrderRealm.insertPreOrder(preOrder)

    fun getPreOrdersRealm(): Flow<List<PreOrderObject>> = preoOrderRealm.getPreOrders()

    suspend fun deleteByPreOrderIdRealm(id: Long) = preoOrderRealm.deletePreOrder(id)

    suspend fun retrySyncRealm(id: Long, isSent: Boolean) = preoOrderRealm.updateIsSent(id, isSent)

}