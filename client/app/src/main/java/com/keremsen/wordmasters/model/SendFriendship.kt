package com.keremsen.wordmasters.model

data class SendFriendship(
    val userId:Int,
    val friendId:Int,
    val Status:Boolean = false
)
