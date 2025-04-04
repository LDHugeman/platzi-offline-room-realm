package com.davidvarela.bizorder.data

import com.davidvarela.bizorder.data.local.realm.OrderObject
import com.davidvarela.bizorder.data.local.realm.PreOrderObject
import com.davidvarela.bizorder.data.local.room.OrderEntity
import com.davidvarela.bizorder.data.local.room.PreOrderEntity
import com.davidvarela.bizorder.domain.Order
import com.davidvarela.bizorder.domain.PreOrder

fun OrderEntity.toDomain(): Order {
    return Order(
        id = id,
        customerName,
        item,
        total,
        imageUrl
    )
}

fun Order.toEntity(): OrderEntity {
    return OrderEntity(
        id = id,
        customerName = customerName,
        item = item,
        total = total,
        imageUrl = imageUrl
    )
}

fun PreOrderEntity.toDomain(): PreOrder {
    return PreOrder(
        id = id,
        customerName = customerName,
        product = item,
        isSent = isSent
    )
}

//Realm
fun OrderObject.toDomain(): Order {
    return Order (
        id = id,
        customerName, item, total, imageUrl
    )
}

fun Order.toRealm(): OrderObject {
    return OrderObject().apply {
        id = this@toRealm.id
        customerName = this@toRealm.customerName
        item = this@toRealm.item
        total = this@toRealm.total
        imageUrl = this@toRealm.imageUrl
    }
}

fun PreOrderObject.toDomain(): PreOrder {
    return PreOrder(
        id = id,
        customerName = customerName,
        product = item,
        isSent = isSent
    )
}