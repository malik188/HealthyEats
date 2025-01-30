package com.example.healthyeats.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.healthyeats.AuthenticationActivity
import com.example.healthyeats.R
import com.example.healthyeats.databinding.FragmentHistoryBinding
import com.example.healthyeats.databinding.FragmentProfileBinding
import com.example.healthyeats.ui.change.ChangeDataActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    private var token: String = ""
    private var name: String = ""
    private var targetCalories: Int = 0
    private var consumeCalories: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
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

        binding.tvUbahData.setOnClickListener{
            startActivity(Intent(requireContext(), ChangeDataActivity::class.java))
        }

        binding.tvLogout.setOnClickListener{
            viewModel.saveToken("")
            startActivity(Intent(requireContext(), AuthenticationActivity::class.java))
        }
    }

    fun setUserData(){
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            launch {
                viewModel.readUser(token).collect{response ->
                    response.onSuccess {
                        name = it.data?.user_name.toString()
                        binding.tvName.text = name
                    }
                    response.onFailure {
                        Toast.makeText(
                            requireContext(),
                            "Gagal mengambil data" + name,
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