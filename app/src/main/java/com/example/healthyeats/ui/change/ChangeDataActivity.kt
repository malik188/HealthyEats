package com.example.healthyeats.ui.change

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.healthyeats.MainActivity
import com.example.healthyeats.R
import com.example.healthyeats.databinding.ActivityChangeDataBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChangeDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangeDataBinding
    private val viewModel: ChangeDataViewModel by viewModels()
    private var token: String = ""
    private var name: String = ""
    private var age: String = ""
    private var weight: String = ""
    private var height: String = ""
    private var gender: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            launch{
                viewModel.getToken().collect{
                    if (!it.isNullOrEmpty()) token = it
                }
            }
        }
        setView()
        setPreData()
        updateUser()
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

    private fun setPreData(){
        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.readUser(token).collect{response ->
                    response.onSuccess {
                        name = it.data?.user_name.toString()
                        age = it.data?.user_age.toString()
                        weight = it.data?.user_weight.toString()
                        height = it.data?.user_height.toString()

                        binding.edtNama.setText(name)
                        binding.edtUsia.setText(age)
                        binding.edtBerat.setText(weight)
                        binding.edtTinggi.setText(height)

                    }
                    response.onFailure {
                        Toast.makeText(this@ChangeDataActivity,
                            "Gagal mengambil data " + name,
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("Gagal mengambil data ", name)
                    }
                }
            }
        }
    }

    private fun updateUser(){

        binding.btnSave.setOnClickListener{
            name = binding.edtNama.text.toString()
            age = binding.edtUsia.text.toString()
            weight = binding.edtBerat.text.toString()
            height = binding.edtTinggi.text.toString()

            val selectedGender : Int = binding.rbGroup.checkedRadioButtonId
            val btn = findViewById<RadioButton>(selectedGender)

            if (btn.text == "Laki-laki"){
                gender = "MALE"
            } else if (btn.text == "Perempuan"){
                gender = "FEMALE"
            }

            lifecycleScope.launchWhenResumed {
                launch {
                    viewModel.updateUser(token, name, age.toInt(), gender, height.toInt(), weight.toInt()).collect{response ->
                        response.onSuccess {
                            Toast.makeText(this@ChangeDataActivity,
                                it.message,
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(this@ChangeDataActivity, MainActivity::class.java))
                        }
                        response.onFailure {
                            Toast.makeText(this@ChangeDataActivity,
                                "Gagal mengambil data " + name,
                                Toast.LENGTH_SHORT
                            ).show()
                            Log.d("Gagal mengambil data ", name)
                        }
                    }
                }
            }
        }
    }
}