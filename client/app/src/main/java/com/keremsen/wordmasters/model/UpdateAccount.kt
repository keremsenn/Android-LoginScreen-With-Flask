package com.keremsen.wordmasters.model

data class UpdateAccount(
    val user_id: String,
    val new_email: String,
    val new_nick_name: String,
    val current_password: String,
    val new_password: String
)