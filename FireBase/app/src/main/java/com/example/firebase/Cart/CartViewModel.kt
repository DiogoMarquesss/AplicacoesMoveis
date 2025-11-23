package com.example.firebase.Cart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firebase.Models.CartModel
import com.example.firebase.Models.Item
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class CartViewModel : ViewModel(){

    private val db =  Firebase.firestore

    var uiState = mutableStateOf(CartModel())
    var uiItem = mutableStateOf(Item())

    fun createCart(userId : String?){
        val uid = userId ?: return
        if(uid.isBlank()) return
        db.collection("Carts")
            .document(uid)
            .set(uiState.value)
    }

    fun updateItemsInCart(item: Item){
        val currentItem = uiState.value.items.toMutableList()
        currentItem.add(item)
        uiState.value = uiState.value.copy(
            items = currentItem
        )
    }
    fun showCartItems(){
        for(item in uiState.value.items){
            uiItem.value = uiItem.value.copy(
                name = item.name,
                price = item.price
            )
        }
    }
    fun addItemCart(userId: String){
        db.collection("Carts")
            .document(userId)
            .set(uiState.value)
    }
}