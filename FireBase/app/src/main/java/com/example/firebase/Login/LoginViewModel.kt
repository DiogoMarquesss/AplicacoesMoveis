package com.example.firebase.Login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.firebase.Login.AuthSession
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

data class LoginState (
    var id : String? = null,
    var email : String? = null,
    var password : String? = null,
    var error : String? = null,
    var isLoading : Boolean = false,
)

class LoginViewModel: ViewModel(){

    private val auth : FirebaseAuth = Firebase.auth


    var uiState = mutableStateOf(LoginState())
    val currentUser = auth.currentUser

    fun updateEmail(email : String) {
        uiState.value = uiState.value.copy(email = email)
    }

    fun updatePassword(password : String) {
        uiState.value = uiState.value.copy(password = password)
    }


    fun login(){
        uiState.value = uiState.value.copy(isLoading = true)
        auth.signInWithEmailAndPassword(
            uiState.value.email ?: "",
            uiState.value.password ?: "",
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    AuthSession.isLogged.value = true
                    Log.d(TAG, "signInWithEmail:success")
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    uiState.value = uiState.value.copy(
                        isLoading = false,
                        error = "Wrong password or no internet connection")
                    AuthSession.isLogged.value = false
                }
            }
    }

    fun logOut(){
        auth.signOut()
        AuthSession.isLogged.value = false
    }

}