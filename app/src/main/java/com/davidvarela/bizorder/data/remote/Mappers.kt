package com.davidvarela.bizorder.data.remote

import com.davidvarela.bizorder.domain.Order

fun OrderDto.toDomain(): Order {
    return Order(
        id = id,
        customerName = customerName,
        item = item,
        total = total,
        imageUrl = imageUrl
    )
}