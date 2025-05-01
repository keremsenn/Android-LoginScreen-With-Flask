package com.keremsen.wordmasters.model

data class NickNameChange(
    val userId:Int,
    val newNickName:String,
    val password:String
)
