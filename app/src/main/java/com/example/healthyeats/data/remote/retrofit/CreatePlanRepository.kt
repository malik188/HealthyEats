package com.example.healthyeats.data.remote.retrofit

import com.example.healthyeats.data.local.TokenPreference
import com.example.healthyeats.data.remote.response.CreatePlanResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CreatePlanRepository @Inject constructor(private val apiService: ApiService, private val preference: TokenPreference) {

    suspend fun createPlan(token: String, name: String, goal: String, activity: String, caloriesTarget: Int) : Flow<Result<CreatePlanResponse>> = flow {
        try {
            val bearerToken = generateToken(token)
            val response = apiService.createPlan(bearerToken, name, goal, activity, caloriesTarget)
            emit(Result.success(response))
        }catch (e: Exception){
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }

    fun getToken(): Flow<String?> = preference.getToken()

    private fun generateToken(token: String): String{
        return "Bearer $token"
    }
}