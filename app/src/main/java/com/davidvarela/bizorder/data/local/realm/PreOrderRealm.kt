package com.davidvarela.bizorder.data.local.realm

import io.realm.kotlin.Realm
import io.realm.kotlin.delete
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreOrderRealm @Inject constructor(
    private val realm: Realm
){

    suspend fun insertPreOrder(preOrderObject: PreOrderObject) {
        realm.write {
            copyToRealm(preOrderObject)
        }
    }

    fun getPreOrders(): Flow<List<PreOrderObject>> {
        return realm.query(PreOrderObject::class).asFlow().map {
            it.list
        }
    }

    suspend fun deletePreOrder(id: Long) {
        realm.write {
            query(PreOrderObject::class, "id == $0", id).first().find()?.let {
                delete(it)
            }
        }
    }

    suspend fun updateIsSent(id: Long, isSent: Boolean) {
        realm.write {
            query(PreOrderObject::class, "id == $0", id).first().find()?.let {
                it.isSent = isSent
            }
        }
    }
}