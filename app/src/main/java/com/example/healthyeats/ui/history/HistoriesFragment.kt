package com.example.healthyeats.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthyeats.adapter.HistoryAdapter
import com.example.healthyeats.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HistoriesFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HistoriesViewModel by viewModels()
    private var token: String = ""
    private lateinit var rvHistory: RecyclerView
    private lateinit var listAdapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
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

        setListHistories()

        val linearLayoutManager = LinearLayoutManager(requireContext())
        listAdapter = HistoryAdapter()

        rvHistory = binding.rvHistories
        rvHistory.apply {
            adapter = listAdapter
            layoutManager = linearLayoutManager
        }
    }


    fun setListHistories(){
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            launch {
                viewModel.getHistory(token).collect{ response ->
                    response.onSuccess {
                        val rvState = rvHistory.layoutManager?.onSaveInstanceState()
                        listAdapter.submitList(it.data)
                        rvHistory.layoutManager?.onRestoreInstanceState(rvState)
                    }

                    response.onFailure {
                        Toast.makeText(
                            requireContext(),
                            "Gagal mengambil data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}