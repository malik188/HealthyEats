package com.example.healthyeats.data.remote.retrofit

import com.example.healthyeats.data.local.TokenPreference
import com.example.healthyeats.data.remote.response.UpdateUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpdateUserRepository @Inject constructor(private val apiService: ApiService, private val preference: TokenPreference) {
    suspend fun updateUser(token : String, name : String, age : Int, gender : String, height : Int, weight : Int) : Flow<Result<UpdateUserResponse>> = flow {
        try {
            val bearerToken = generateToken(token)
            val response = apiService.updateUser(bearerToken, name, age, gender, height, weight)
            emit(Result.success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    fun getToken(): Flow<String?> = preference.getToken()

    private fun generateToken(token: String): String{
        return "Bearer $token"
    }
}