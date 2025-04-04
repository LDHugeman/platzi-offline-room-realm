package com.davidvarela.bizorder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidvarela.bizorder.domain.PreOrder
import com.davidvarela.bizorder.domain.PreOrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CreateEvent {
    data object Success : CreateEvent()
    data object Error : CreateEvent()
}

data class PreOrderState(
    val isLoading: Boolean = false,
    val data: List<PreOrder> = emptyList(),
    val isError: Boolean = false
)

@HiltViewModel
class PreOrderViewModel @Inject constructor(
    private val preOrderRepository: PreOrderRepository
): ViewModel() {

    var eventFlow = MutableSharedFlow<CreateEvent>()
        private set

    var preOrderState:StateFlow<PreOrderState> = preOrderRepository.getPreOrders()
        .map { result ->
            result.fold(
                onSuccess = { data ->
                    PreOrderState(data = data, isLoading = false)
                },
                onFailure = { data ->
                    PreOrderState(isError = true, isLoading = false)
                }
            )
        }.stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PreOrderState(isLoading = true)
        )

    fun onSavePreOrder(customerName: String, product: String) {
        viewModelScope.launch {
            val result = preOrderRepository.savePreOrder(
                PreOrder(customerName = customerName, product = product)
            )
            if (result.isSuccess) {
                eventFlow.emit(CreateEvent.Success)
            } else {
                eventFlow.emit(CreateEvent.Error)
            }
        }
    }

    fun onDeletePreOrder(id: Long) {
        viewModelScope.launch {
            preOrderRepository.deletePreOrder(id)
        }
    }

    fun onSyncPreOrder(id: Long) {
        viewModelScope.launch {
            preOrderRepository.retrySync(id)
        }
    }
}