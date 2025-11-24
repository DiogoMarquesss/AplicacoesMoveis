package com.example.firebase.User.Model

data class UserInfoModel(
    val name: String = "",
    val address: String = "",
    val id : String = "",
    val cartId : String = "",
    val isEditing : Boolean = false
)