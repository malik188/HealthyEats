package com.example.healthyeats.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthyeats.data.remote.response.ReadPlanResponse
import com.example.healthyeats.data.remote.response.ReadUserResponse
import com.example.healthyeats.data.remote.retrofit.LoginRepository
import com.example.healthyeats.data.remote.retrofit.ReadPlanRepository
import com.example.healthyeats.data.remote.retrofit.ReadUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val loginRepository: LoginRepository, private val readUserRepository: ReadUserRepository, private val readPlanRepository: ReadPlanRepository) : ViewModel() {

    fun getToken(): Flow<String?> = loginRepository.getToken()

    suspend fun readUser(token: String) : Flow<Result<ReadUserResponse>> = readUserRepository.readUser(token)

    suspend fun readPlan(token: String) : Flow<Result<ReadPlanResponse>> = readPlanRepository.readPlan(token)
}