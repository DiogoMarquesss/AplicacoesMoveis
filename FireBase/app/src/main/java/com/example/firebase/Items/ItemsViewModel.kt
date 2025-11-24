package com.example.firebase.Items

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase.Models.Item
import com.example.firebase.Repository.ItemRepository
import com.example.firebase.Repository.ResultWrapper
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class FetchItems(
    var items: List<Item> = emptyList(),
    val error: String? = null,
    var isLoading : Boolean = false
)
@HiltViewModel
class ItemsViewModel @Inject constructor (private val repository: ItemRepository) : ViewModel(){

    val itemState = mutableStateOf(Item())

    val uiState = mutableStateOf(FetchItems())
    fun fetchItems(){
        repository.getItemsFromDb().onEach { result ->
            when(result){
                is ResultWrapper.Loading ->{
                    uiState.value = uiState.value.copy(
                        isLoading = true
                    )
                }
                is ResultWrapper.Success -> {
                    val items = result.data ?: emptyList()
                    uiState.value = uiState.value.copy(
                        items = items,
                        isLoading = false,
                        error = null,
                    )
                    listToItem(items = items)
                }
                is ResultWrapper.Error -> {
                    uiState.value = uiState.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
    fun listToItem(items: List<Item>){
        for (item in items){
            itemState.value = item
            Log.d("ITEMS", "Item individual: $item")
        }
    }
}



