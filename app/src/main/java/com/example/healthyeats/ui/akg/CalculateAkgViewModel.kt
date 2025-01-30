package com.example.healthyeats.ui.akg

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.healthyeats.data.remote.response.CreatePlanResponse
import com.example.healthyeats.data.remote.response.ReadPlanResponse
import com.example.healthyeats.data.remote.response.ReadUserResponse
import com.example.healthyeats.data.remote.response.UpdatePlanResponse
import com.example.healthyeats.data.remote.retrofit.ApiService
import com.example.healthyeats.data.remote.retrofit.CreatePlanRepository
import com.example.healthyeats.data.remote.retrofit.LoginRepository
import com.example.healthyeats.data.remote.retrofit.ReadPlanRepository
import com.example.healthyeats.data.remote.retrofit.ReadUserRepository
import com.example.healthyeats.data.remote.retrofit.UpdatePlanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class CalculateAkgViewModel @Inject constructor(private val loginRepository: LoginRepository, private val readPlanRepository: ReadPlanRepository, private val updatePlanRepository: UpdatePlanRepository, private val readUserRepository: ReadUserRepository, private val createPlanRepository: CreatePlanRepository) : ViewModel() {

    private var targetCallories : Float = 0.0F

    fun getToken(): Flow<String?> = loginRepository.getToken()

    suspend fun readPlan(token: String) : Flow<Result<ReadPlanResponse>> = readPlanRepository.readPlan(token)

    suspend fun readUser(token: String) : Flow<Result<ReadUserResponse>> = readUserRepository.readUser(token)

    suspend fun updatePlan(token: String, planName: String, planGoal: String, planActivity: String, gender: String, weight: Int, height: Int, age: Int) : Flow<Result<UpdatePlanResponse>> {
        val activityLess = 1.2
        val activityLessBit = 1.375
        val activityActive = 1.55
        val activityVeryActive = 1.725

        if (gender == "MALE"){
            when(planGoal){
                "Menaikkan Berat Badan" -> {
                    when(planActivity){
                        "Sangat Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityVeryActive).toFloat()
                        }
                        "Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityActive).toFloat()
                        }
                        "Sedikit Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityLessBit).toFloat()
                        }
                        "Jarang Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityLess).toFloat()
                        }
                    }

                    targetCallories += 500
                }
                "Menurunkan Berat Badan" -> {
                    when(planActivity){
                        "Sangat Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityVeryActive).toFloat()
                        }
                        "Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityActive).toFloat()
                        }
                        "Sedikit Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityLessBit).toFloat()
                        }
                        "Jarang Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityLess).toFloat()
                        }
                    }

                    targetCallories -= 500
                }
            }
        }else if (gender == "FEMALE"){
            when(planGoal){
                "Menaikkan Berat Badan" -> {
                    when(planActivity){
                        "Sangat Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityVeryActive).toFloat()
                        }
                        "Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityActive).toFloat()
                        }
                        "Sedikit Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityLessBit).toFloat()
                        }
                        "Jarang Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityLess).toFloat()
                        }
                    }

                    targetCallories += 500
                }
                "Menurunkan Berat Badan" -> {
                    when(planActivity){
                        "Sangat Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityVeryActive).toFloat()
                        }
                        "Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityActive).toFloat()
                        }
                        "Sedikit Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityLessBit).toFloat()
                        }
                        "Jarang Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityLess).toFloat()
                        }
                    }

                    targetCallories -= 500
                }
            }
        }

        Log.d("ViewDATA", targetCallories.toString())

        return updatePlanRepository.updatePlan(token, planName, planGoal, planActivity, targetCallories.toInt())
    }

    suspend fun createPlan(token: String, planName: String, planGoal: String, planActivity: String, gender: String, weight: Int, height: Int, age: Int) : Flow<Result<CreatePlanResponse>> {
        val activityLess = 1.2
        val activityLessBit = 1.375
        val activityActive = 1.55
        val activityVeryActive = 1.725

        if (gender == "MALE"){
            when(planGoal){
                "Menaikkan Berat Badan" -> {
                    when(planActivity){
                        "Sangat Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityVeryActive).toFloat()
                        }
                        "Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityActive).toFloat()
                        }
                        "Sedikit Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityLessBit).toFloat()
                        }
                        "Jarang Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityLess).toFloat()
                        }
                    }

                    targetCallories += 500
                }
                "Menurunkan Berat Badan" -> {
                    when(planActivity){
                        "Sangat Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityVeryActive).toFloat()
                        }
                        "Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityActive).toFloat()
                        }
                        "Sedikit Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityLessBit).toFloat()
                        }
                        "Jarang Aktif" ->{
                            targetCallories = ((66.5 + (13.75 * weight) + (5 * height) - (6.75 * age) ) * activityLess).toFloat()
                        }
                    }

                    targetCallories -= 500
                }
            }
        }else if (gender == "FEMALE"){
            when(planGoal){
                "Menaikkan Berat Badan" -> {
                    when(planActivity){
                        "Sangat Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityVeryActive).toFloat()
                        }
                        "Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityActive).toFloat()
                        }
                        "Sedikit Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityLessBit).toFloat()
                        }
                        "Jarang Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityLess).toFloat()
                        }
                    }

                    targetCallories += 500
                }
                "Menurunkan Berat Badan" -> {
                    when(planActivity){
                        "Sangat Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityVeryActive).toFloat()
                        }
                        "Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityActive).toFloat()
                        }
                        "Sedikit Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityLessBit).toFloat()
                        }
                        "Jarang Aktif" ->{
                            targetCallories = ((655.1 + (9.56 * weight) + (1.85 * height) - (4.68 * age) ) * activityLess).toFloat()
                        }
                    }

                    targetCallories -= 500
                }
            }
        }

        Log.d("ViewDATA", targetCallories.toString())

        return createPlanRepository.createPlan(token, planName, planGoal, planActivity, targetCallories.toInt())
    }
}