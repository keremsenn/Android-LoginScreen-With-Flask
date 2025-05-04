package com.keremsen.wordmasters.model

data class User(
    val id: String,
    val nickName: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val winCount: Int,
    val matchCount: Int,
    val isActive: Boolean,
    val createdAt: String
)