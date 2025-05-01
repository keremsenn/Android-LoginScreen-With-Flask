package com.keremsen.wordmasters.model

data class RegisterRequest(
    val nickName: String,
    val firstName: String,
    val lastName: String,
    val email:String,
    val password:String
)
