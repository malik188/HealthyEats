package com.example.healthyeats.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UploadPredictResponse (
    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("foodName")
    val foodName: String? = null,

    @field:SerializedName("foodPro")
    val foodPro: Float? = null,

    @field:SerializedName("foodCalc")
    val foodCalc: Float? = null,

    @field:SerializedName("foodFat")
    val foodFat: Float? = null,

    @field:SerializedName("foodCarbo")
    val foodCarbo: Float? = null,

    @field:SerializedName("foodVit")
    val foodVit: Int? = null,

    @field:SerializedName("foodCal")
    val foodCal: Int? = null,

    @field:SerializedName("predictedProb")
    val predictedProb: Float? = null,

    @field:SerializedName("calorieTarget")
    val calorieTarget: Int? = null,

    @field:SerializedName("calorieConsume")
    val calorieConsume: Int? = null,
) : Parcelable