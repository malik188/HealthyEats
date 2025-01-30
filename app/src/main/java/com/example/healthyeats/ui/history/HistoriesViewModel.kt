package com.example.healthyeats.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.healthyeats.data.remote.response.HistoryResponse
import com.example.healthyeats.data.remote.retrofit.ApiService
import com.example.healthyeats.data.remote.retrofit.HistoryRepository
import com.example.healthyeats.data.remote.retrofit.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HistoriesViewModel @Inject constructor(private val loginRepository: LoginRepository, private val historyRepository: HistoryRepository) : ViewModel() {

    fun getToken(): Flow<String?> = loginRepository.getToken()

    suspend fun getHistory(token: String) : Flow<Result<HistoryResponse>> = historyRepository.getHistory(token)
}