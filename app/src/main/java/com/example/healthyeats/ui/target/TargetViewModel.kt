package com.example.healthyeats.ui.target

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthyeats.data.remote.response.ReadPlanResponse
import com.example.healthyeats.data.remote.retrofit.ApiService
import com.example.healthyeats.data.remote.retrofit.LoginRepository
import com.example.healthyeats.data.remote.retrofit.ReadPlanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TargetViewModel @Inject constructor(private val loginRepository: LoginRepository,private val readPlanRepository: ReadPlanRepository) : ViewModel() {
    fun getToken(): Flow<String?> = loginRepository.getToken()
    suspend fun readPlan(token: String) : Flow<Result<ReadPlanResponse>> = readPlanRepository.readPlan(token)
}