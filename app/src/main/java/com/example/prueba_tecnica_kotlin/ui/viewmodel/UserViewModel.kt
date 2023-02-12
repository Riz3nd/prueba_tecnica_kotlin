package com.example.prueba_tecnica_kotlin.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba_tecnica_kotlin.domain.GetUsers
import com.google.gson.JsonArray
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    var userModel = MutableLiveData<JsonArray>()
    val isLoading = MutableLiveData<Boolean>()
    var getUsers = GetUsers()

    fun onCreate(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result: JsonArray? = getUsers()
            if (result != null){
                userModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
}