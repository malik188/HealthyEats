package com.example.healthyeats.ui.target

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.healthyeats.databinding.FragmentTargetBinding
import com.example.healthyeats.ui.akg.CalculateAkgActivity
import com.example.healthyeats.ui.change.ChangeDataActivity
import com.example.healthyeats.ui.detect.DetectActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TargetFragment : Fragment() {

    private var _binding: FragmentTargetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TargetViewModel by viewModels()
    private var token: String = ""
    private var targetCalories: Int = 0
    private var consumeCalories: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTargetBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            launch{
                viewModel.getToken().collect(){
                    if (!it.isNullOrEmpty()) token = it
                }
            }
        }

        setData()

        binding.btnAkg.setOnClickListener{
            startActivity(Intent(requireContext(), CalculateAkgActivity::class.java))
        }

        binding.fabTarget.setOnClickListener{
            startActivity(Intent(requireContext(), DetectActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setData(){
        lifecycleScope.launchWhenResumed {
            launch {
                viewModel.readPlan(token).collect{ response ->
                    response.onSuccess {
                        targetCalories = it.data?.plan?.calories_target ?: 0
                        consumeCalories = it.data?.plan?.calories_consume ?: 0
                        binding.sisaKalori.text = targetCalories.toString()
                        binding.konsumsiKalori.text = consumeCalories.toString()
                    }
                    response.onFailure {
                        Log.d("Gagal", consumeCalories.toString())
                    }
                }
            }
        }
    }
}