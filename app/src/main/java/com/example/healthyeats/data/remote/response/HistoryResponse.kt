package com.example.healthyeats.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class HistoryResponse (
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: List<HistoryObject>
    )

@Parcelize
data class HistoryObject(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("imageUrl")
    val imageUrl: String,

    @field:SerializedName("foodName")
    val foodName: String,

    @field:SerializedName("foodCal")
    val foodCal: Int
) : Parcelable