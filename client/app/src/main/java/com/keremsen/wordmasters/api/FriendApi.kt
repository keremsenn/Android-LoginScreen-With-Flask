package com.keremsen.wordmasters.api

import com.keremsen.wordmasters.model.RegisterRequest
import com.keremsen.wordmasters.model.SendFriendship
import com.keremsen.wordmasters.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Query

interface FriendApi {

    @POST("friends/send_friendship")
    suspend fun sendFriendship(@Body friendship: SendFriendship)

    @POST("friends/update_status")
    suspend fun updateFriendStatus(@Body friendship: SendFriendship)

    @DELETE("friends/delete")
    suspend fun deleteFriend(@Query("id") id:Int)
}