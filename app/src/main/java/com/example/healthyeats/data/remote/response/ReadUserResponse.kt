package com.example.healthyeats.data.remote.response

import com.google.gson.annotations.SerializedName

data class ReadUserResponse (
    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: ReadUserData? = null
)

data class ReadUserData (
    @field:SerializedName("user_id")
    val user_id: Int? = null,

    @field:SerializedName("user_name")
    val user_name: String? = null,

    @field:SerializedName("user_email")
    val user_email: String? = null,

    @field:SerializedName("user_age")
    val user_age: Int? = null,

    @field:SerializedName("user_gender")
    val user_gender: String? = null,

    @field:SerializedName("user_height")
    val user_height: Int? = null,

    @field:SerializedName("user_weight")
    val user_weight: Int? = null
)