package com.example.firebase.User

import android.util.Log
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firebase.User.Model.UserInfoModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class UserProfileViewModel : ViewModel(){

    private val auth : FirebaseAuth = Firebase.auth
    private val db = Firebase.firestore
    val uiState = mutableStateOf(UserInfoModel())

    fun getUserInfo(userId: String){
        db.collection("Users").document(userId).get()
            .addOnSuccessListener { documet ->
                if(documet.exists()){
                    val name = documet.getString("name") ?: ""
                    val address = documet.getString("address") ?: ""

                    uiState.value = uiState.value.copy(
                        name = name,
                        address = address
                    )
                }
            }
            .addOnFailureListener { e ->
                Log.e("Firestore", "Erro ao buscar info do usuário", e)
            }
    }

    fun updateUserInfo(userId: String){
        db.collection("Users").document(userId)
            .update(
                "name", uiState.value.name,
                "address", uiState.value.address
            )
    }

    fun updateUserId() {
        uiState.value = uiState.value.copy(
            id = auth.currentUser?.uid ?: ""
        )
    }
    fun updateIsEditing(){
        uiState.value = uiState.value.copy(
            isEditing = true
        )
    }
    fun updateName(name : String){
        uiState.value = uiState.value.copy(
            name = name
        )
    }
    fun updateAddress(address : String){
        uiState.value = uiState.value.copy(
            address = address
        )
    }

}