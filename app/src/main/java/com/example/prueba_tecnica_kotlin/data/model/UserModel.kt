package com.example.prueba_tecnica_kotlin.data.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class UserModel(@SerializedName("user") var user:List<JsonObject>)