package com.davidvarela.bizorder.data

import com.davidvarela.bizorder.data.remote.RemoteDataStorage
import com.davidvarela.bizorder.data.remote.toDomain
import com.davidvarela.bizorder.domain.Order
import com.davidvarela.bizorder.domain.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class OrderRepositoryImpl(
    private val remoteDataStorage: RemoteDataStorage,
    private val localDataStorage: LocalDataStorage
) : OrderRepository {
    override fun getOrders(): Flow<Result<List<Order>>> {
        return localDataStorage.getOrdersRealm()
            .map { localOrders ->
                Result.success(localOrders.map { it.toDomain() })
            }.onStart {
                val remoteResult = remoteDataStorage.getOrders().mapCatching { list ->
                    list.map { it.toDomain() }
                }

                remoteResult.getOrNull()?.let { remoteOrders ->
                    localDataStorage.insertOrdersRealm(remoteOrders.map { it.toRealm() })
                }
            }.catch { exception ->
                emit(Result.failure(exception))
            }
    }

    override suspend fun getOrderById(id: String) =
        localDataStorage.getOrderByOrderIdRealm(id).mapCatching {
            it?.toDomain()
        }

}