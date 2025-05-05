package com.keremsen.wordmasters.api

import retrofit2.Response
import com.keremsen.wordmasters.model.LoginRequest
import com.keremsen.wordmasters.model.UpdateAccount
import com.keremsen.wordmasters.model.RegisterRequest
import com.keremsen.wordmasters.model.RegisterResponse
import com.keremsen.wordmasters.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {

    @POST("users/login")
    suspend fun  login(@Body loginRequest:LoginRequest) : Response<User?>

    @POST("users/register")
    suspend fun register(@Body registerRequest: RegisterRequest) : Response<RegisterResponse>

    @POST("users/update_is_active")
    suspend fun updateIsActive(@Body user: User)

    @GET("users/id")
    suspend fun getById(@Query("id") id:Int) : User

    @GET("users/all")
    suspend fun getAll() : List<User>

    @GET("users/nick_name")
    suspend fun getByNickName(@Query("nick_name") nickName:String) : User

    @DELETE("users/delete")
    suspend fun deleteById(@Query("id") id: Int) : User

    @PATCH("users/update_account")
    suspend fun updateAccount(@Body updateAccount: UpdateAccount)


}