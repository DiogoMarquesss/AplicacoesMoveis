package com.example.firebase.Login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebase.Login.AuthSession
import com.example.firebase.Repository.ItemRepository
import com.example.firebase.Repository.LoginRepository
import com.example.firebase.Repository.ResultWrapper
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class LoginState (
    var id : String? = null,
    var email : String? = null,
    var password : String? = null,
    var error : String? = null,
    var isLoading : Boolean = false,
)

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: LoginRepository) : ViewModel(){

    var uiState = mutableStateOf(LoginState())

    fun updateEmail(email : String) {
        uiState.value = uiState.value.copy(email = email)
    }

    fun updatePassword(password : String) {
        uiState.value = uiState.value.copy(password = password)
    }

    fun login(){
        repository.tryLogin(uiState.value.email!!, uiState.value.password!!)
            .onEach { result ->
                when(result){
                    is ResultWrapper.Loading ->{
                        uiState.value = uiState.value.copy(isLoading = true, error = null)
                    }
                    is ResultWrapper.Success -> {
                        uiState.value = uiState.value.copy(isLoading = false, error = null)
                    }
                    is ResultWrapper.Error -> {
                        uiState.value = uiState.value.copy(
                            isLoading = false,
                            error = result.message
                        )
                    }
                }
            }.launchIn(viewModelScope)

    }

}