package com.davidvarela.bizorder.ui.theme.data.remote

import javax.inject.Inject

class RemoteDataStorage @Inject constructor(
    val bizOrderApi: BizOrderApi
) {

    suspend fun getOrders() = runCatching {
        val response = bizOrderApi.getOrders()
        if (response.isSuccessful) {
            response.body().orEmpty()
        } else {
            throw Exception("${response.code()}: ${response.message()}")
        }
    }

    suspend fun savePreOrders() = runCatching {
        val response = bizOrderApi.savePreOrders()
        if (response.isSuccessful) {
            response.body() ?: Unit
        } else {
            throw Exception("${response.code()}: ${response.message()}")
        }
    }
}