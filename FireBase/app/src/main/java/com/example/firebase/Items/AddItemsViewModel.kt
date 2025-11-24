package com.example.firebase.Items

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firebase.Models.Item
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class AddItemsViewModel : ViewModel() {

    val uiState = mutableStateOf(Item())

    val db = Firebase.firestore

    fun updateItemName(name: String){
        uiState.value = uiState.value.copy(name = name)
    }
    fun updateItemPrice(price: String){
        uiState.value = uiState.value.copy(price = price)
    }
    fun updateItemId(itemId: String){
        uiState.value = uiState.value.copy(id = itemId)
    }
    fun addItems() {
        val item = uiState.value
        db.collection("Items")
            .document("Pecas")
            .collection("listaPecas")
            .add(item)
            .addOnSuccessListener { docRef ->
                Log.d(TAG, "Peça adicionada com id=${docRef.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Erro ao adicionar peça", e)
            }
    }
}
