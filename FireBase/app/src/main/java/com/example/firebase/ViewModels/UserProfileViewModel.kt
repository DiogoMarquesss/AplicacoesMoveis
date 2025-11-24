package com.example.firebase.ViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class UserProfileViewModel : ViewModel(){

    private val auth : FirebaseAuth = Firebase.auth

    val uiState = mutableStateOf(UserInfo())


    fun updateName(name: String?) {
        uiState.value = uiState.value.copy(name = name)
    }

    fun updateAddress(address: String?) {
        uiState.value = uiState.value.copy(address = address)
    }

}