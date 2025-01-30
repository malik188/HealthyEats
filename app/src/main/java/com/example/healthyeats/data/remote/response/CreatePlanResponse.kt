package com.example.healthyeats.data.remote.response

import com.google.gson.annotations.SerializedName

data class CreatePlanResponse (
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("message")
    val message: String
)