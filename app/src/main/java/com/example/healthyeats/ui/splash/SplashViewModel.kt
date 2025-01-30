package com.example.healthyeats.ui.splash

import androidx.lifecycle.ViewModel
import com.example.healthyeats.data.remote.retrofit.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor (private val loginRepository: LoginRepository) : ViewModel() {

    fun getToken(): Flow<String?> = loginRepository.getToken()
}