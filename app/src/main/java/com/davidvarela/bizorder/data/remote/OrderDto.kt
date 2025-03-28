package com.davidvarela.bizorder.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrderDto(
    val id: String,
    val item: String,
    @Json(name = "customer_name")
    val customerName: String,
    val total: Double,
    @Json(name = "image_url")
    val imageUrl: String
)
