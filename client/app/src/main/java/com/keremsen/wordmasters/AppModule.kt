package com.keremsen.wordmasters

import android.app.Application
import android.content.Context
import com.keremsen.wordmasters.api.FriendApi
import com.keremsen.wordmasters.api.LevelApi
import com.keremsen.wordmasters.api.RetrofitInstance
import com.keremsen.wordmasters.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }

    @Provides
    fun provideUserApi(): UserApi {
        return RetrofitInstance.userApi}

    @Provides
    fun provideLevelApi(): LevelApi {
        return RetrofitInstance.levelApi}

    @Provides
    fun provideFriendApi(): FriendApi {
        return RetrofitInstance.friendApi}
}