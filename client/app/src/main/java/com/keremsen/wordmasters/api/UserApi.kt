package com.keremsen.wordmasters.api

import com.keremsen.wordmasters.model.EmailChange
import com.keremsen.wordmasters.model.LoginRequest
import com.keremsen.wordmasters.model.NickNameChange
import com.keremsen.wordmasters.model.PasswordChange
import com.keremsen.wordmasters.model.RegisterRequest
import com.keremsen.wordmasters.model.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {

    @POST("users/login")
    suspend fun  login(@Body loginRequest:LoginRequest) : User

    @POST("user/register")
    suspend fun register(@Body registerRequest: RegisterRequest)

    @POST("user/update_is_active")
    suspend fun updateIsActive(@Body user: User)

    @GET("user/id")
    suspend fun getById(@Query("id") id:Int) : User

    @GET("user/nick_name")
    suspend fun getByNickName(@Query("nick_name") nickName:String) : User

    @DELETE("user/delete")
    suspend fun deleteById(@Query("id") id: Int) : User

    @POST("users/password_change")
    suspend fun passwordChange(@Body passwordChange: PasswordChange)

    @POST("users/email_change")
    suspend fun emailChange(@Body emailChange: EmailChange)

    @POST("users/nick_name_change")
    suspend fun nickNameChange(@Body nickNameChange: NickNameChange)




}