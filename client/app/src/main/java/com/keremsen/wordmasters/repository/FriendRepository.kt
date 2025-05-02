package com.keremsen.wordmasters.repository

import android.content.Context
import com.keremsen.wordmasters.api.FriendApi
import com.keremsen.wordmasters.model.Friend
import com.keremsen.wordmasters.model.FriendData
import com.keremsen.wordmasters.model.SendFriendship
import javax.inject.Inject


class FriendRepository @Inject constructor(
    private  val friendApi: FriendApi,
    private  val context: Context
){

    suspend fun sendFriendship(friendship: SendFriendship){
        return friendApi.sendFriendship(friendship)
    }
    suspend fun updateFriendStatus(friendStatus: SendFriendship){
        return friendApi.updateFriendStatus(friendStatus)
    }
    suspend fun deleteFriend( id:Int){
        return friendApi.deleteFriend(id)
    }
    suspend fun getByUserId( friendData: FriendData):List<Friend>{
        return friendApi.getByUserId(friendData)
    }

}