package com.iamashad.unitrackrrr.screens.fee

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamashad.unitrackrrr.model.FeeReceipt
import com.iamashad.unitrackrrr.repository.ReceiptRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceiptViewModel @Inject constructor(
    private val repository: ReceiptRepository
) : ViewModel() {

    private val _receipts = MutableStateFlow<List<FeeReceipt>>(emptyList())
    val receipts = _receipts.asStateFlow()

    init {
        fetchUserReceipts()
    }

    private fun fetchUserReceipts() {
        viewModelScope.launch {
            repository.getUserReceipts().collect { receiptList ->
                _receipts.value = receiptList
            }
        }
    }

    private val _receiptsBySemester = MutableStateFlow<List<FeeReceipt>>(emptyList())
    val receiptsBySemester: StateFlow<List<FeeReceipt>> = _receiptsBySemester

    fun fetchReceiptsBySemester(semesterId: Int) {
        viewModelScope.launch {
            _receiptsBySemester.value = repository.getReceiptsBySemester(semesterId)
        }
    }
}
