package com.example.healthyeats.data.remote.response

import com.google.gson.annotations.SerializedName

data class ReadPlanResponse (
    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("data")
    val data: PlanData? = null
)

data class PlanData(
    @field:SerializedName("plan")
    val plan: ReadPlanData? = null
)

data class ReadPlanData (
    @field:SerializedName("plan_id")
    val plan_id: Int? = null,

    @field:SerializedName("plan_name")
    val plan_name: String? = null,

    @field:SerializedName("plan_goal")
    val plan_goal: String? = null,

    @field:SerializedName("plan_activity")
    val plan_activity: String? = null,

    @field:SerializedName("calories_target")
    val calories_target: Int? = null,

    @field:SerializedName("calories_consume")
    val calories_consume: Int? = null,

    @field:SerializedName("user_id")
    val user_id: Int? = null
)