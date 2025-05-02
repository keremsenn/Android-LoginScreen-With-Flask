package com.keremsen.wordmasters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keremsen.wordmasters.api.LevelApi
import com.keremsen.wordmasters.model.Level
import com.keremsen.wordmasters.repository.LevelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LevelViewModel @Inject constructor(
  private val  levelRepository: LevelRepository
) : ViewModel(){

    private val _level = MutableStateFlow<Level?>(null)
    val level : StateFlow<Level?> = _level

    fun getById(id : Int ) {
        viewModelScope.launch {
            try {
                _level.value = levelRepository.getById(id)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun getByUserId(userId : Int ){
        viewModelScope.launch {
            try {
                _level.value = levelRepository.getByUserId(userId)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun updateLevel(level: Level){
        viewModelScope.launch {
            try {
                levelRepository.updateLevel(level)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}