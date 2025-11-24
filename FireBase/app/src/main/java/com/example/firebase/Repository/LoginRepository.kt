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
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val auth: FirebaseAuth
){
    fun tryLogin(email : String, password: String) : Flow<ResultWrapper<Unit>> = flow{
        emit(ResultWrapper.Loading())
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            emit(ResultWrapper.Success(Unit))
        }catch (e: Exception){
            emit(ResultWrapper.Error(e.localizedMessage?:"Unexpected Error"))
        }
    }.flowOn(Dispatchers.IO)

    fun tryLogOut() : Flow<ResultWrapper<Unit>> = flow{
        emit(ResultWrapper.Loading())
        try {
            auth.signOut()
            emit(ResultWrapper.Success(Unit))
        }catch (e: Exception){
            emit(ResultWrapper.Error(e.localizedMessage?:"Unexpected Error"))
        }
    }

    fun tryGetUser(): String?{
        return auth.currentUser?.uid
    }
}

