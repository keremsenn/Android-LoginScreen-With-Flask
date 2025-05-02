package com.keremsen.wordmasters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremsen.wordmasters.model.Friend
import com.keremsen.wordmasters.model.FriendData
import com.keremsen.wordmasters.model.SendFriendship
import com.keremsen.wordmasters.repository.FriendRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FriendViewModel @Inject constructor(
    private val friendRepository: FriendRepository
):ViewModel(){

    private val _friend = MutableStateFlow<Friend?>(null)
    val friend : StateFlow<Friend?> = _friend

    private val _friendList = MutableStateFlow<List<Friend>>(emptyList())
    val friendList : StateFlow<List<Friend>> = _friendList

    fun sendFriendship(friendship: SendFriendship){
        viewModelScope.launch {
            try {
                 friendRepository.sendFriendship(friendship)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun updateFriendStatus(friendStatus: SendFriendship){
        viewModelScope.launch {
            try {
                friendRepository.updateFriendStatus(friendStatus)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun deleteFriend( id:Int){
        viewModelScope.launch {
            try {
                friendRepository.deleteFriend(id)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun getByUserId(friendData: FriendData){
        viewModelScope.launch {
            try {
              _friendList.value = friendRepository.getByUserId(friendData)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}