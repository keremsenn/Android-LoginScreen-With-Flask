package com.keremsen.wordmasters.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:5000/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val userApi: UserApi by lazy {
        retrofit.create(UserApi::class.java)
    }
    val levelApi: LevelApi by lazy {
        retrofit.create(LevelApi::class.java)
    }

    val friendApi:FriendApi by lazy{
        retrofit.create(FriendApi::class.java)
    }
}