package com.keremsen.wordmasters.model

data class Friend(
    val id:Int,
    val userId:Int,
    val friendId:Int,
    val status:Boolean,
    val createdAt:String
)