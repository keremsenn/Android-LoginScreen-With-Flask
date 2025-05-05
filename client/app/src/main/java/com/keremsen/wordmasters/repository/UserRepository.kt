package com.keremsen.wordmasters.repository

import android.content.Context
import com.keremsen.wordmasters.api.UserApi
import com.keremsen.wordmasters.model.LoginRequest
import com.keremsen.wordmasters.model.UpdateAccount
import com.keremsen.wordmasters.model.RegisterRequest
import com.keremsen.wordmasters.model.RegisterResponse
import com.keremsen.wordmasters.model.User
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi,
    private val context: Context
){
    suspend fun  login(loginRequest: LoginRequest) :  Response<User?>{
        return userApi.login(loginRequest)
    }
    suspend fun register(registerRequest: RegisterRequest) : Response<RegisterResponse> {
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
    suspend fun updateAccount(updateAccount: UpdateAccount) {
        return userApi.updateAccount(updateAccount)
    }

}