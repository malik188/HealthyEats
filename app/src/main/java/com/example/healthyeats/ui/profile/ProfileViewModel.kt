package com.example.healthyeats.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthyeats.data.remote.response.ReadPlanResponse
import com.example.healthyeats.data.remote.response.ReadUserResponse
import com.example.healthyeats.data.remote.retrofit.ApiService
import com.example.healthyeats.data.remote.retrofit.LoginRepository
import com.example.healthyeats.data.remote.retrofit.ReadPlanRepository
import com.example.healthyeats.data.remote.retrofit.ReadUserRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val loginRepository: LoginRepository, private val readUserRepository: ReadUserRepository, private val readPlanRepository: ReadPlanRepository) : ViewModel() {
    fun getToken(): Flow<String?> = loginRepository.getToken()

    fun saveToken(token: String){
        viewModelScope.launch {
            loginRepository.saveToken(token)
        }
    }
    suspend fun readUser(token: String) : Flow<Result<ReadUserResponse>> = readUserRepository.readUser(token)

    suspend fun readPlan(token: String) : Flow<Result<ReadPlanResponse>> = readPlanRepository.readPlan(token)
}