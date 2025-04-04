package com.davidvarela.bizorder.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidvarela.bizorder.domain.Order
import com.davidvarela.bizorder.domain.OrderRepository
import com.davidvarela.bizorder.presentation.navigation.Screen.DetailOrder.ARG_ORDER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DetailState(
    val order:Order? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    orderRepository: OrderRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val orderId: String? = savedStateHandle.get<String>(ARG_ORDER_ID)
    var detailState = MutableStateFlow(DetailState())
    private set

    init {
        viewModelScope.launch {
            orderId?.let {
                orderRepository.getOrderById(it).onSuccess { orderDetail ->
                    detailState.value = DetailState(order = orderDetail, isLoading = false)
                }.onFailure {
                    detailState.value = DetailState(isError = true, isLoading = false)
                }
            } ?: run {
                detailState.value = DetailState(isError = true, isLoading = false)
            }
        }
    }
}