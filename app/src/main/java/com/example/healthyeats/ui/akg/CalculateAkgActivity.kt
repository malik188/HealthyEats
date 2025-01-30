package com.example.healthyeats.ui.akg

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.healthyeats.MainActivity
import com.example.healthyeats.R
import com.example.healthyeats.databinding.ActivityCalculateAkgBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
@AndroidEntryPoint
class CalculateAkgActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculateAkgBinding
    private val viewModel: CalculateAkgViewModel by viewModels()
    private var token: String = ""
    private var age: String = ""
    private var weight: String = ""
    private var height: String = ""
    private var gender: String = ""
    private var planName: String = ""
    private var planGoal: String = ""
    private var planActivity: String = ""
    private var targetCalories: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateAkgBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            launch{
                viewModel.getToken().collect(){
                    if (!it.isNullOrEmpty()) token = it
                }
            }
        }

        setView()
        setUserData()
        setPrePlanData()
        setAkg()

    }

    private fun setView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()

    }

    fun setUserData(){
        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.readUser(token).collect{ response ->
                    response.onSuccess {
                        age = it.data?.user_age.toString()
                        weight = it.data?.user_weight.toString()
                        height = it.data?.user_height.toString()
                        gender = it.data?.user_gender.toString()
                    }
                    response.onFailure {
                        Log.d("Gagal", "Gagal mengambil data User")
                    }
                }
            }
        }
    }

    fun setPrePlanData(){
        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.readPlan(token).collect{ response ->
                    response.onSuccess {
                        targetCalories = it.data?.plan?.calories_target  ?:0
                        planName = it.data?.plan?.plan_name.toString()
                        binding.edtPlan.setText(planName)
                    }
                    response.onFailure {
                        Log.d("Gagal", "Gagal mengambil data Plan")
                    }
                }
            }
        }
    }

    fun setAkg(){
        binding.btnHitung.setOnClickListener{
            if (targetCalories == 0){
                planName = binding.edtPlan.text.toString()

                val selectedGoal : Int = binding.rbGroupGoal.checkedRadioButtonId
                val btnGoal = findViewById<RadioButton>(selectedGoal)

                if (btnGoal.text == "Menaikkan Berat Badan"){
                    planGoal = "Menaikkan Berat Badan"
                } else if (btnGoal.text == "Menurunkan Berat Badan"){
                    planGoal = "Menurunkan Berat Badan"
                }

                val selectedActivity : Int = binding.rbGroupActivity.checkedRadioButtonId
                val btnActivity = findViewById<RadioButton>(selectedActivity)

                when (btnActivity.text) {
                    "Sangat Aktif" -> {
                        planActivity = "Sangat Aktif"
                    }
                    "Aktif" -> {
                        planActivity = "Aktif"
                    }
                    "Sedikit Aktif" -> {
                        planActivity = "Sedikit Aktif"
                    }
                    "Jarang Aktif" -> {
                        planActivity = "Jarang Aktif"
                    }
                }

                lifecycleScope.launchWhenResumed {
                    launch {
                        viewModel.createPlan(token, planName, planGoal, planActivity, gender, weight.toInt(), height.toInt(), age.toInt()).collect{ response ->
                            response.onSuccess {
                                Toast.makeText(this@CalculateAkgActivity,
                                    it.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(Intent(this@CalculateAkgActivity, MainActivity::class.java))
                                Log.d("DATA", planName + " " + planGoal + " " + planActivity + " " + gender + " " + weight + " " + height + " " + age)
                            }

                            response.onFailure {
                                Toast.makeText(this@CalculateAkgActivity,
                                    "Gagal membuat plan " + planName,
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.d("Gagal membuat plan ", planName)
                            }
                        }
                    }
                }
            } else{
                planName = binding.edtPlan.text.toString()

                if (binding.rbNaik.isChecked){
                    planGoal = "Menaikkan Berat Badan"
                } else if (binding.rbTurun.isChecked){
                    planGoal = "Menurunkan Berat Badan"
                }

                if (binding.rbVeryActive.isChecked){
                    planActivity = "Sangat Aktif"
                } else if (binding.rbActive.isChecked){
                    planActivity = "Aktif"
                } else if (binding.rbActiveBit.isChecked){
                    planActivity = "Sedikit Aktif"
                } else if (binding.rbLessActive.isChecked){
                    planActivity = "Jarang Aktif"
                }


                lifecycleScope.launchWhenResumed {
                    launch {
                        viewModel.updatePlan(token, planName, planGoal, planActivity, gender, weight.toInt(), height.toInt(), age.toInt()).collect{ response ->
                            response.onSuccess {
                                Toast.makeText(this@CalculateAkgActivity,
                                    it.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(Intent(this@CalculateAkgActivity, MainActivity::class.java))
                                Log.d("DATA", planName + " " + planGoal + " " + planActivity + " " + gender + " " + weight + " " + height + " " + age)
                            }

                            response.onFailure {
                                Toast.makeText(this@CalculateAkgActivity,
                                    "Gagal membuat plan " + planName,
                                    Toast.LENGTH_SHORT
                                ).show()
                                Log.d("Gagal membuat plan ", planName)
                            }
                        }
                    }
                }
            }
        }
    }
}