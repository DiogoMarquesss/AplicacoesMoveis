package com.example.firebase.Items

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firebase.Models.Item
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import javax.inject.Inject

data class FetchItems(
    var items: List<Item> = emptyList()
)
class ItemsViewModel @Inject constructor(

){

    val db = Firebase.firestore

    val uiState = mutableStateOf(FetchItems())
    val itemState = mutableStateOf(Item())

    // Fetch all items in database
    fun getItemsFromDb() {
        db.collection("Items").document("Pecas").collection("listaPecas")
            .get()
            .addOnSuccessListener { docs ->
                val items = docs.documents.mapNotNull { doc ->
                    val itemID = doc.toObject(Item::class.java)
                    itemID?.copy(id = doc.id)
                }

                Log.d("ITEMS", "Carregados ${items.size} items: $items")
                uiState.value = uiState.value.copy(
                    items = items,
                )
            }
            .addOnFailureListener { e ->
                Log.e("ITEMS", "Erro ao buscar items", e)
            }
    }


    fun listToItem(items: List<Item>){
        for (item in items){
            itemState.value = item
            Log.d("ITEMS", "Item individual: $item")

        }
    }

<<<<<<< HEAD
    fun removeItem(itemId: String): Flow<ResultWrapper<Unit>> = flow {
        repository.removeItem(itemId).onEach { result ->
            when(result){
                is ResultWrapper.Loading ->{
                    uiState.value = uiState.value.copy(
                        isLoading = true
                    )
                }
                is ResultWrapper.Success -> {
                    uiState.value = uiState.value.copy(
                        isLoading = false,
                        error = null,
                    )
                }
                is ResultWrapper.Error -> {
                    uiState.value = uiState.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }


=======
    fun removeItem(itemId: String){
        db.collection("Items").document("Pecas").collection("listaPecas")
            .document(itemId)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }
>>>>>>> parent of a70960e (clean code)
}

