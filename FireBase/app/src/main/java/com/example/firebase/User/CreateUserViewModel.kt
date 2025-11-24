package com.example.firebase.User

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.firebase.Cart.CartViewModel
import com.example.firebase.Login.LoginState
import com.example.firebase.User.Model.UserInfoModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

data class UserInfo(
    var name: String? = null,
    var address: String? = null,
)

class CreateUserViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    var uiState = mutableStateOf(UserInfo())
    var userModel = mutableStateOf(UserInfoModel())
    var loginState = mutableStateOf(LoginState())



    fun updateName(name: String) {
        uiState.value = uiState.value.copy(name = name)
    }

    fun updateAddress(address: String) {
        uiState.value = uiState.value.copy(address = address)
    }

    fun updateEmail(email: String) {
        loginState.value = loginState.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        loginState.value = loginState.value.copy(password = password)
    }

    fun createUser(onResult: (Boolean, String?) -> Unit) {
        Log.d("Teste_Botao", "Teste create")

        if (!isDataComplete()) {
            onResult(false, "")
            return
        }

        auth.createUserWithEmailAndPassword(
            loginState.value.email ?: "",
            loginState.value.password ?: "",
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val uid = auth.currentUser?.uid
                Log.d(TAG, "signInWithEmail:success")
                onResult(true, uid)
            } else {
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                loginState.value = loginState.value.copy(
                    isLoading = false,
                    error = "Wrong password or no internet connection"
                )
                onResult(false, "")
            }
        }
    }

    fun isDataComplete(): Boolean {
        loginState.value = loginState.value.copy(isLoading = true)

        if (loginState.value.email.isNullOrBlank() || loginState.value.password.isNullOrBlank()) {
            loginState.value = loginState.value.copy(
                isLoading = false,
                error = "Email e password não podem estar vazios"
            )
            return false
        }
        return true
    }

    fun AddUserInfo() {
        Log.d("Teste_Botao", "addd")
        val userId = auth.currentUser?.uid ?: return
        db.collection("Users")
            .document(userId)
            .set(uiState.value)
    }
}
