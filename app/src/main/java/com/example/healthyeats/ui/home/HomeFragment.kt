package com.example.healthyeats.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.healthyeats.databinding.FragmentHomeBinding
import com.example.healthyeats.ui.akg.CalculateAkgActivity
import com.example.healthyeats.ui.detect.DetectActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private var token: String = ""
    private var name: String = ""
    private var height: String = ""
    private var weight: String = ""
    private var targetCalories: Int = 0
    private var consumeCalories: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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

        setUserData()
        setPlanData()

        binding.fabTarget.setOnClickListener{
            startActivity(Intent(requireContext(), DetectActivity::class.java))
        }
    }

    fun setUserData(){
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            launch {
                viewModel.readUser(token).collect{response ->
                    response.onSuccess {
                        name = it.data?.user_name.toString()
                        weight = it.data?.user_weight.toString()
                        height = it.data?.user_height.toString()

                        binding.nama.text = name
                        binding.berat.text = weight
                        binding.tinggi.text = height
                    }
                    response.onFailure {
                        Toast.makeText(
                            requireContext(),
                            "Gagal mengambil data User" + name,
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("Gagal", name)
                    }
                }
            }
        }
    }

    fun setPlanData(){
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            launch {
                viewModel.readPlan(token).collect{response ->
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