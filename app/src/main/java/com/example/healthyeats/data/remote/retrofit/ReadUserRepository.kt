package com.example.healthyeats.data.remote.retrofit

import com.example.healthyeats.data.remote.response.ReadUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ReadUserRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun readUser(token: String) : Flow<Result<ReadUserResponse>> = flow {
        try {
            val bearerToken = generateToken(token)
            val response = apiService.readUser(bearerToken)
            emit(Result.success(response))
        }catch (e: Exception){
            e.printStackTrace()
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    private fun generateToken(token: String): String{
        return "Bearer $token"
    }
}