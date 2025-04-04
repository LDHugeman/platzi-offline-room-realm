package com.davidvarela.bizorder.data

import com.davidvarela.bizorder.data.local.realm.PreOrderObject
import com.davidvarela.bizorder.data.local.room.PreOrderEntity
import com.davidvarela.bizorder.data.remote.RemoteDataStorage
import com.davidvarela.bizorder.domain.PreOrder
import com.davidvarela.bizorder.domain.PreOrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreOrdersRepositoryImpl(
    private val remoteDataStorage: RemoteDataStorage,
    private val localDataStorage: LocalDataStorage
): PreOrderRepository {
    override suspend fun savePreOrder(preOrder: PreOrder) =
        remoteDataStorage.savePreOrders().also { result ->
            localDataStorage.savePreOrderRoom(
                PreOrderEntity(
                    id = preOrder.id,
                    customerName = preOrder.customerName,
                    item = preOrder.product,
                    isSent = result.isSuccess
                )
                /*PreOrderObject().apply {
                    customerName = preOrder.customerName
                    item = preOrder.product
                    isSent = result.isSuccess
                }*/
            )
        }

    override fun getPreOrders(): Flow<Result<List<PreOrder>>> {
        return localDataStorage.getPreOrdersRealm().map { preOrders ->
            runCatching {
                preOrders.map { it.toDomain() }
            }
        }
    }

    override suspend fun deletePreOrder(id: Long) = localDataStorage.deleteByPreOrderIdRealm(id)

    override suspend fun retrySync(id: Long) {
        val result = remoteDataStorage.savePreOrders()
        if (result.isSuccess) {
            localDataStorage.retrySyncRealm(id, true)
        }
    }
}