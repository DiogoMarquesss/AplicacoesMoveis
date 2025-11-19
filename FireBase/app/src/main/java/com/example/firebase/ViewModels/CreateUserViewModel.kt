package com.example.firebase.ViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

class CreateUserViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    val user = UserInfo()

    var uiState = mutableStateOf(UserInfo())

    fun updateName(name: String) {
        uiState.value = uiState.value.copy(name = name)
    }

    fun AddUserInfo() {
        val userId = auth.currentUser?.uid ?: return
        db.collection("Users")
            .document(userId)
            .set(uiState.value)
    }
}
