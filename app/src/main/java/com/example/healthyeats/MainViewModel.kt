package com.example.healthyeats


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthyeats.data.remote.retrofit.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {
    fun getToken(): Flow<String?> = loginRepository.getToken()

    fun saveToken(token: String){
        viewModelScope.launch {
            loginRepository.saveToken(token)
        }
    }
}