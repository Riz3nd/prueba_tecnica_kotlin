package com.example.prueba_tecnica_kotlin.data.network

import com.google.gson.JsonArray
import retrofit2.Response
import retrofit2.http.GET

interface UserApiClient {
    @GET("/users")
    suspend fun getAllUsers(): Response<JsonArray>
}