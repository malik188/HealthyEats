package com.example.healthyeats.data.remote.retrofit

import com.example.healthyeats.data.remote.response.UploadPredictResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

class UploadPredictRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun uploadPredict(token: String, file: MultipartBody.Part) : Flow<Result<UploadPredictResponse>> = flow {
        try {
            val bearerToken = generateToken(token)
            val response = apiService.uploadPredict(bearerToken, file)
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