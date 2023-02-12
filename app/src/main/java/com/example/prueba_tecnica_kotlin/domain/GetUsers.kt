package com.example.prueba_tecnica_kotlin.domain

import com.example.prueba_tecnica_kotlin.data.UserRepository
import com.google.gson.JsonArray

class GetUsers {
    private val repository = UserRepository()
    suspend operator fun invoke(): JsonArray? = repository.getAllUsers()
}