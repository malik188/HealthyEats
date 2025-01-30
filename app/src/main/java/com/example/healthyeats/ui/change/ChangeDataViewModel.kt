package com.example.healthyeats.ui.change

import androidx.lifecycle.ViewModel
import com.example.healthyeats.data.remote.response.ReadUserResponse
import com.example.healthyeats.data.remote.response.UpdateUserResponse
import com.example.healthyeats.data.remote.retrofit.LoginRepository
import com.example.healthyeats.data.remote.retrofit.ReadUserRepository
import com.example.healthyeats.data.remote.retrofit.UpdateUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ChangeDataViewModel @Inject constructor(private val loginRepository: LoginRepository, private val updateUserRepository: UpdateUserRepository, private val readUserRepository: ReadUserRepository) : ViewModel(){
    fun getToken(): Flow<String?> = loginRepository.getToken()

    suspend fun updateUser(token : String, name : String, age : Int, gender : String, height : Int, weight : Int) : Flow<Result<UpdateUserResponse>> = updateUserRepository.updateUser(token, name, age, gender, height, weight)

    suspend fun readUser(token: String) : Flow<Result<ReadUserResponse>> = readUserRepository.readUser(token)
}