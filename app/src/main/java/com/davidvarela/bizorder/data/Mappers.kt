package com.davidvarela.bizorder.data

import com.davidvarela.bizorder.data.local.room.OrderEntity
import com.davidvarela.bizorder.domain.Order

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