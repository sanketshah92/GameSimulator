package com.sanket.iplsimulator.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sanket.iplsimulator.MainActivity
import com.sanket.iplsimulator.R
import com.sanket.iplsimulator.databinding.FragmentWinnerBinding
import com.sanket.iplsimulator.presentation.IPLSimulatorViewModel

class GameWinnerFragment : Fragment() {
    private lateinit var binding: FragmentWinnerBinding
    private lateinit var viewModel: IPLSimulatorViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_winner, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareViewModel()
    }


    private fun prepareViewModel() {
        viewModel = (activity as MainActivity).viewModel
        viewModel.getWinners().observe(viewLifecycleOwner, Observer { winners ->
            val winner = winners.keys.first()
            val runnerUp = winners.keys.last()
            binding.txtPosition1.text = winners[winner]?.name
            binding.txtRunnerUp1Team.text = winners[runnerUp]?.name
        })
    }
}