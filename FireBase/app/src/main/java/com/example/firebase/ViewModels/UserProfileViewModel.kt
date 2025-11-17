package com.example.firebase.ViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

data class UserInfo(
    var id : String? = null,
    var email : String? = null,
    var name : String? = null,
    var address : String? = null
)

class UserProfileViewModel : ViewModel(){

    private val auth : FirebaseAuth = Firebase.auth

    val uiState = mutableStateOf(UserInfo())


    fun updateId(id: String) {
        uiState.value = uiState.value.copy(id = id)
    }

    fun updateEmail(email: String?) {
        uiState.value = uiState.value.copy(email = email)
    }

    fun updateName(name: String?) {
        uiState.value = uiState.value.copy(name = name)
    }

    fun updateAddress(address: String?) {
        uiState.value = uiState.value.copy(address = address)
    }

    fun GetInfoUser(){
        val user = auth.currentUser ?: return
        updateId(user.uid)
        updateEmail(user.email)
    }
}