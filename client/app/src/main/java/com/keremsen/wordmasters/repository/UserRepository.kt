package com.keremsen.wordmasters.repository

import android.content.Context
import com.keremsen.wordmasters.api.UserApi
import com.keremsen.wordmasters.model.EmailChange
import com.keremsen.wordmasters.model.LoginRequest
import com.keremsen.wordmasters.model.NickNameChange
import com.keremsen.wordmasters.model.PasswordChange
import com.keremsen.wordmasters.model.RegisterRequest
import com.keremsen.wordmasters.model.User
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi,
    private val context: Context
){
    suspend fun  login(loginRequest: LoginRequest) : User{
        return userApi.login(loginRequest)
    }
    suspend fun register(registerRequest: RegisterRequest) {
        return userApi.register(registerRequest)
    }
    suspend fun updateIsActive(user: User) {
        return userApi.updateIsActive(user)
    }
    suspend fun getById(id:Int) : User {
        return userApi.getById(id)
    }
    suspend fun getAll() : List<User> {
        return userApi.getAll()
    }
    suspend fun getByNickName(nickName:String) : User {
        return userApi.getByNickName(nickName)
    }
    suspend fun deleteById( id: Int) : User {
        return userApi.deleteById(id)
    }
    suspend fun passwordChange( passwordChange: PasswordChange) {
        return userApi.passwordChange(passwordChange)
    }
    suspend fun emailChange( emailChange: EmailChange) {
        return userApi.emailChange(emailChange)
    }
    suspend fun nickNameChange( nickNameChange: NickNameChange) {
        return userApi.nickNameChange(nickNameChange)
    }

}