package com.example.prueba_tecnica_kotlin.data

import com.example.prueba_tecnica_kotlin.data.network.UserService
import com.google.gson.JsonArray

class UserRepository {
    private val api = UserService()

    suspend fun getAllUsers(): JsonArray {
        val response: JsonArray = api.getUser()
        return response
    }
}