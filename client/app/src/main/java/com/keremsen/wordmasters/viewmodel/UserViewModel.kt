package com.keremsen.wordmasters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremsen.wordmasters.model.EmailChange
import com.keremsen.wordmasters.model.LoginRequest
import com.keremsen.wordmasters.model.NickNameChange
import com.keremsen.wordmasters.model.PasswordChange
import com.keremsen.wordmasters.model.RegisterRequest
import com.keremsen.wordmasters.model.User
import com.keremsen.wordmasters.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel(){
    private val _user = MutableStateFlow<User?>(null)
    val user : StateFlow<User?> = _user

    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList : StateFlow<List<User>> = _userList

    fun  login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                    _user.value = userRepository.login(loginRequest)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

    }
    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            try {
                userRepository.register(registerRequest)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun updateIsActive(user: User) {
        viewModelScope.launch {
            try {

            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun getById(id:Int) {
        viewModelScope.launch {
            try {
                _user.value = userRepository.getById(id)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun getAll() {
        viewModelScope.launch {
            try {
                _userList.value = userRepository.getAll()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun getByNickName(nickName:String)  {
        viewModelScope.launch {
            try {
                _user.value = userRepository.getByNickName(nickName)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun deleteById( id: Int)  {
        viewModelScope.launch {
            try {
                userRepository.deleteById(id)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun passwordChange( passwordChange: PasswordChange) {
        viewModelScope.launch {
            try {
                userRepository.passwordChange(passwordChange)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun emailChange( emailChange: EmailChange) {
        viewModelScope.launch {
            try {
                userRepository.emailChange(emailChange)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
    fun nickNameChange( nickNameChange: NickNameChange) {
        viewModelScope.launch {
            try {
                userRepository.nickNameChange(nickNameChange)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

}