package com.davidvarela.bizorder.data.local.realm

import io.realm.kotlin.Realm
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OrderRealm @Inject constructor(
    private val realm: Realm
) {

    suspend fun insertOrders(ordersEntity: List<OrderObject>) {
        realm.write {
            ordersEntity.forEach { ordersEntity ->
                copyToRealm(ordersEntity)
            }
        }
    }

    fun getOrders(): Flow<List<OrderObject>> {
        return  realm.asFlow().map { realChange ->
            realChange.realm.query(OrderObject::class).find()
        }
    }
    suspend fun getOrder(id: String): OrderObject? {
        return realm.query(OrderObject::class, "id == $0", id).first().find()
    }
}