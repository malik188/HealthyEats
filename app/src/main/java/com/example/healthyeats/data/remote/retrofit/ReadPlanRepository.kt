package com.example.healthyeats.data.remote.retrofit

import com.example.healthyeats.data.local.TokenPreference
import com.example.healthyeats.data.remote.response.ReadPlanResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ReadPlanRepository @Inject constructor(private val apiService: ApiService, private val preference: TokenPreference) {
    suspend fun readPlan(token: String): Flow<Result<ReadPlanResponse>> = flow {
        try {
            val bearerToken = generateToken(token)
            val response = apiService.readPlan(bearerToken)
            emit(Result.success(response))
        }catch (e: Exception){
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    fun getToken(): Flow<String?> = preference.getToken()

    private fun generateToken(token: String): String{
        return "Bearer $token"
    }
}