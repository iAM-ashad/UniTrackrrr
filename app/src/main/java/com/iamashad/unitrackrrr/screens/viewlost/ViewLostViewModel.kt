package com.iamashad.unitrackrrr.screens.viewlost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamashad.unitrackrrr.model.LostItem
import com.iamashad.unitrackrrr.repository.LostItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewLostViewModel @Inject constructor(
    private val repository: LostItemRepository
) : ViewModel() {

    private val _Items = MutableStateFlow<List<LostItem>>(emptyList())
    val items: StateFlow<List<LostItem>> = _Items

    init {
        fetchLostItems()
    }

    private fun fetchLostItems() {
        viewModelScope.launch {
            _Items.value = repository.getAllLostItems()
        }
    }
}
