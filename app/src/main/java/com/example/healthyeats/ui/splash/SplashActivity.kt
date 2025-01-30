package com.example.healthyeats.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.healthyeats.AuthenticationActivity
import com.example.healthyeats.MainActivity
import com.example.healthyeats.MainActivity.Companion.TOKEN
import com.example.healthyeats.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        determineDirection()
    }

    private fun determineDirection(){
        lifecycleScope.launchWhenCreated {
            launch {
                viewModel.getToken().collect{ token ->
                    if (token.isNullOrEmpty()) {
                        Intent(this@SplashActivity, AuthenticationActivity::class.java).also {
                            startActivity(it)
                            finish()
                        }
                    } else {
                        Intent(this@SplashActivity, MainActivity::class.java).also {
                            it.putExtra(TOKEN, token)
                            startActivity(it)
                            finish()
                        }
                    }
                }
            }
        }
    }
}