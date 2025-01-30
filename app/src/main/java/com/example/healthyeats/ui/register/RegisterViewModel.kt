package com.example.healthyeats.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthyeats.data.remote.retrofit.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {

    suspend fun accountRegister(name: String, email: String, password: String) = loginRepository.accountRegister(name, email, password)
}