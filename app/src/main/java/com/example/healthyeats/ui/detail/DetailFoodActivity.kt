package com.example.healthyeats.ui.detail

import android.Manifest
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.example.healthyeats.MainActivity
import com.example.healthyeats.R
import com.example.healthyeats.data.remote.response.UploadPredictResponse
import com.example.healthyeats.databinding.ActivityDetailFoodBinding
import com.example.healthyeats.ui.change.ChangeDataActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setView()

        val foodData = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<UploadPredictResponse>(DATA, UploadPredictResponse::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<UploadPredictResponse>(DATA)
        }

        binding.tvNama.text = foodData?.foodName
        binding.tvCal.text = foodData?.foodCal.toString()
        binding.tvPro.text = foodData?.foodPro.toString()
        binding.tvCalc.text = foodData?.foodCalc.toString()
        binding.tvFat.text = foodData?.foodFat.toString()
        binding.tvCarbo.text = foodData?.foodCarbo.toString()
        binding.tvVit.text = foodData?.foodVit.toString()

        binding.btnSelesai.setOnClickListener{
            startActivity(Intent(this@DetailFoodActivity, MainActivity::class.java))
        }
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

    companion object {
        const val DATA = "data"
    }
}