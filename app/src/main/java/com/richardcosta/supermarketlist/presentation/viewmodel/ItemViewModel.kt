package com.richardcosta.supermarketlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richardcosta.supermarketlist.data.Item
import com.richardcosta.supermarketlist.data.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {

    val allItems: Flow<List<Item>> = repository.allItems

    fun addItem(name: String, quantity: Int) {
        viewModelScope.launch {
            repository.addItem(Item(name = name, quantity = quantity))
        }
    }

    fun removeItem(item: Item) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
}