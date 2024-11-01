package com.iamashad.unitrackrrr.screens.viewfound

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamashad.unitrackrrr.model.FoundItem
import com.iamashad.unitrackrrr.repository.FoundItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewFoundViewModel @Inject constructor(
    private val repository: FoundItemRepository
) : ViewModel() {

    private val _foundItems = MutableStateFlow<List<FoundItem>>(emptyList())
    val foundItems: StateFlow<List<FoundItem>> = _foundItems

    init {
        fetchFoundItems()
    }

    private fun fetchFoundItems() {
        viewModelScope.launch {
            _foundItems.value = repository.getAllFoundItems()
        }
    }
}