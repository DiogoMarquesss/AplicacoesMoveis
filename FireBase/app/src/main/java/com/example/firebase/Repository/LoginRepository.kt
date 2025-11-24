package com.example.firebase.Repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.firebase.Login.AuthSession
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val auth: FirebaseAuth
){
    fun tryLogin(email : String, password: String) : Flow<ResultWrapper<Unit>> = flow{
        try {
            emit(ResultWrapper.Loading())
            auth.signInWithEmailAndPassword(email, password)
            emit(ResultWrapper.Success(Unit))
        }catch (e: Exception){
            emit(ResultWrapper.Error(e.localizedMessage?:"Unexpected Error"))
        }
    }.flowOn(Dispatchers.IO)

    fun logOut(){
        auth.signOut()
        AuthSession.isLogged.value = false
    }
}

