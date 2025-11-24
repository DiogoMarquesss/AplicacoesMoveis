package com.example.firebase.Repository

import com.example.firebase.Models.CartModel
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn



import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firebase.Models.Item
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class ItemRepository @Inject constructor(
    private val db: FirebaseFirestore
){

    // Fetch all items in database
    fun getItemsFromDb() : Flow<ResultWrapper<List<Item>>> = flow {
        try {
            emit(ResultWrapper.Loading())
            val docRef = db
                .collection("Items").document("Pecas").collection("listaPecas")

            docRef
                .snapshotFlow()
                .collect {
                    val items = mutableListOf<Item>()
                    for (doc in it.documents ?: emptyList()) {
                        val item = doc.toObject(Item::class.java)
                        item?.id = doc.id
                        item?.let {
                            items.add(item)
                        }
                    }
                    emit(ResultWrapper.Success(items.toList()))
                }
        }catch (e: Exception){
            emit(ResultWrapper.Error(e.localizedMessage?:"Unexpected Error"))
        }
    }.flowOn(Dispatchers.IO)

    fun removeItem(itemId: String){
        db.collection("Items").document("Pecas").collection("listaPecas")
            .document(itemId)
            .delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

}

