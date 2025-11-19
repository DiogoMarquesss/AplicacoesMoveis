package com.example.firebase.ViewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

data class Item(
    var name : String? = null
)
class AddItemsViewModel : ViewModel() {

    val uiState = mutableStateOf(Item())

    val db = Firebase.firestore

    fun updateItemName(name: String){
        uiState.value = uiState.value.copy(name = name)
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
