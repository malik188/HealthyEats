package com.example.healthyeats.data.remote.retrofit

import com.example.healthyeats.data.remote.response.CreatePlanResponse
import com.example.healthyeats.data.remote.response.HistoryResponse
import com.example.healthyeats.data.remote.response.LoginResponse
import com.example.healthyeats.data.remote.response.ReadPlanData
import com.example.healthyeats.data.remote.response.ReadPlanResponse
import com.example.healthyeats.data.remote.response.ReadUserResponse
import com.example.healthyeats.data.remote.response.RegisterResponse
import com.example.healthyeats.data.remote.response.UpdatePlanResponse
import com.example.healthyeats.data.remote.response.UpdateUserResponse
import com.example.healthyeats.data.remote.response.UploadPredictResponse
import okhttp3.MultipartBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query
import java.io.File

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun accountLogin(
        @Field("email") email: String,
        @Field("pass") password: String
    ) : LoginResponse

    @FormUrlEncoded
    @POST("register")
    suspend fun accountRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("pass") password: String
    ): RegisterResponse
    
    @GET("readUser")
    suspend fun readUser(
        @Header("Authorization") token: String
    ) : ReadUserResponse

    @FormUrlEncoded
    @PUT("updateUser")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Field("name") name: String,
        @Field("age") age: Int,
        @Field("gender") gender: String,
        @Field("height") height: Int,
        @Field("weight") weight: Int
    ) : UpdateUserResponse

    @FormUrlEncoded
    @POST("createPlan")
    suspend fun createPlan(
        @Header("Authorization") token: String,
        @Field("name") name: String,
        @Field("goal") goal: String,
        @Field("activity") activity: String,
        @Field("calories_target") calories_target: Int
    ) : CreatePlanResponse

    @FormUrlEncoded
    @PUT("updatePlan")
    suspend fun updatePlan(
        @Header("Authorization") token: String,
        @Field("name") name: String,
        @Field("goal") goal: String,
        @Field("activity") activity: String,
        @Field("calories_target") calories_target: Int
    ) : UpdatePlanResponse

    @GET("readPlan")
    suspend fun readPlan(
        @Header("Authorization") token: String
    ) : ReadPlanResponse

    @Multipart
    @POST("classifyImage")
    suspend fun uploadPredict(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ) : UploadPredictResponse

    @GET("getHistory")
    suspend fun getHistory(
        @Header("Authorization") token: String
    ) : HistoryResponse
}