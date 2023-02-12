package com.example.prueba_tecnica_kotlin.data.network

import com.example.prueba_tecnica_kotlin.core.RetrofitClient
import com.google.gson.JsonArray
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserService {
    private val retrofit = RetrofitClient.getClient()

    suspend fun getUser(): JsonArray {
        return withContext(Dispatchers.IO){
            val resp: Response<JsonArray> = retrofit.create(UserApiClient::class.java).getAllUsers();
            resp.body() ?: JsonArray();
        }
    }
}