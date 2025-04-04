package com.davidvarela.bizorder.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidvarela.bizorder.domain.PreOrder
import com.davidvarela.bizorder.domain.PreOrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class CreateEvent {
    data object Success : CreateEvent()
    data object Error : CreateEvent()
}

@HiltViewModel
class PreOrderViewModel @Inject constructor(
    private val preOrderRepository: PreOrderRepository
): ViewModel() {

    var eventFlow = MutableSharedFlow<CreateEvent>()
        private set

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
}