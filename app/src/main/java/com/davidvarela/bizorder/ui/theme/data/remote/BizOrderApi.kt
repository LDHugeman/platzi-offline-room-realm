package com.davidvarela.bizorder.ui.theme.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface BizOrderApi {

    @GET("orders")
    suspend fun getOrders(): Response<List<OrderDto>>

    @POST("/pre_order_success")
    suspend fun savePreOrders(): Response<Unit>


}