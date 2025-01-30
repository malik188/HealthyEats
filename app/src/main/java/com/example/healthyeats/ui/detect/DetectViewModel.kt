package com.example.healthyeats.ui.detect

import androidx.lifecycle.ViewModel
import com.example.healthyeats.data.remote.response.UploadPredictResponse
import com.example.healthyeats.data.remote.retrofit.LoginRepository
import com.example.healthyeats.data.remote.retrofit.UploadPredictRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class DetectViewModel @Inject constructor(private val loginRepository: LoginRepository, private val uploadPredictRepository: UploadPredictRepository) : ViewModel() {
    fun getToken(): Flow<String?> = loginRepository.getToken()

    suspend fun uploadPredict(token: String, file: MultipartBody.Part): Flow<Result<UploadPredictResponse>> = uploadPredictRepository.uploadPredict(token, file)
}