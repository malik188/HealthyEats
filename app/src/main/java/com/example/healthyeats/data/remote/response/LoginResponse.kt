package com.example.healthyeats.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: DataUser? = null
)

data class DataUser (
    @field:SerializedName("token")
    val token: String? = null
)