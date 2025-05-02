package com.keremsen.wordmasters.repository

import android.content.Context
import com.keremsen.wordmasters.api.LevelApi
import com.keremsen.wordmasters.model.Level
import javax.inject.Inject

class LevelRepository @Inject constructor(
   private val levelApi: LevelApi,
   private val context: Context
){


    suspend fun getById( id : Int ) : Level{
        return levelApi.getById(id)
    }
    suspend fun getByUserId( userId : Int ) : Level{
        return  levelApi.getByUserId(userId)
    }
    suspend fun updateLevel(level: Level){
        return levelApi.updateLevel(level)
    }
}