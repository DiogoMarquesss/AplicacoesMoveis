package com.example.firebase.ViewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

data class LoginState (
    var email : String? = null,
    var password : String? = null,
    var error : String? = null,
    var isLoading : Boolean = false
)

class LoginViewModel: ViewModel(){

    private val auth : FirebaseAuth = Firebase.auth

    val uiState = mutableStateOf(LoginState())


    fun updateEmail(email : String) {
        uiState.value = uiState.value.copy(email = email)
    }

    fun updatePassword(password : String) {
        uiState.value = uiState.value.copy(password = password)
    }
    fun createUser(){

        uiState.value = uiState.value.copy(isLoading = true)

        auth.createUserWithEmailAndPassword(
            uiState.value.email ?: "",
            uiState.value.password ?: "",
        ).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Log.d(TAG, "signInWithEmail:success")
                val user = auth.currentUser
            }else{
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                uiState.value = uiState.value.copy(
                    isLoading = false,
                    error = "Wrong password or no internet connection")
            }
        }
    }

    fun login(){
        uiState.value = uiState.value.copy(isLoading = true)
        auth.signInWithEmailAndPassword(
            uiState.value.email ?: "",
            uiState.value.password ?: "",
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    uiState.value = uiState.value.copy(
                        isLoading = false,
                        error = "Wrong password or no internet connection")
                }
            }
    }
}