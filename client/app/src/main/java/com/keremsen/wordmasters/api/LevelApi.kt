package com.keremsen.wordmasters.api

import com.keremsen.wordmasters.model.Level
import com.keremsen.wordmasters.model.User
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LevelApi {

    @GET("levels/id")
    suspend fun getById(@Query("id") id : Int ) : Level

    @GET("levels/user_id")
    suspend fun getByUserId(@Query("user_id") userId : Int ) : Level

    @POST("levels/update_level")
    suspend fun updateLevel(@Body level: Level)


}