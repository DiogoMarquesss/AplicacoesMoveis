package com.example.firebase.Components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.firebase.Login.LoginState
import com.example.firebase.Repository.LoginRepository
import com.example.firebase.Repository.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

data class NavBarState(
    var uId : String = ""
)
@HiltViewModel
class NavBarVieWModel @Inject constructor(private val repository: LoginRepository): ViewModel(){

    val userState = mutableStateOf(NavBarState())
    val uiState = mutableStateOf(LoginState())

    fun getUser(){
        val uid = repository.tryGetUser()
        if (uid != null){
            userState.value = userState.value.copy(
                uId = uid
            )
        }
    }

    fun logOut(){
        repository.tryLogOut().onEach { result ->
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