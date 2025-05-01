package com.keremsen.wordmasters.model

data class PasswordChange(
    val userId: Int,
    val currentPassword: String,
    val newPassword: String
)
