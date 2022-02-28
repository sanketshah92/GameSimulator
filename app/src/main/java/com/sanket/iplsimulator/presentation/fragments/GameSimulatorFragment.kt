package com.sanket.iplsimulator.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.sanket.iplsimulator.MainActivity
import com.sanket.iplsimulator.R
import com.sanket.iplsimulator.data.models.IPLTeam
import com.sanket.iplsimulator.databinding.FragmentGameSimulationBinding
import com.sanket.iplsimulator.presentation.IPLSimulatorViewModel
import com.sanket.iplsimulator.presentation.adapters.GameSimulatorAdapter

class GameSimulatorFragment : Fragment() {
    private lateinit var binding: FragmentGameSimulationBinding
    private lateinit var viewModel: IPLSimulatorViewModel
    private val adapter = GameSimulatorAdapter()
    private var currentTeams = mutableListOf<List<IPLTeam>>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game_simulation,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView()
        prepareViewModel()
        doSimulate()
    }

    private fun prepareViewModel() {
        viewModel = (activity as MainActivity).viewModel
        viewModel.getIPLTeam().observe(viewLifecycleOwner, Observer {
            viewModel.getTeamPairs(it).observe(viewLifecycleOwner, Observer { games ->
                decideNavigationLabel(games.size)
                setupGames(games)
            })
        })
    }

    private fun decideNavigationLabel(gameSize: Int) {
        if (gameSize < 2) {
            binding.btnSimulate.text = "Simulate & End"
        } else {
            binding.btnSimulate.text = "Simulate"
        }
    }

    private fun doSimulate() {
        binding.btnSimulate.setOnClickListener {
            if (!binding.btnSimulate.text.equals("Simulate")) {
                viewModel.simulateGame(currentTeams).observe(viewLifecycleOwner, Observer {
                    setupGames(it)
                    decideNavigationLabel(it.size)
                    val action =
                        GameSimulatorFragmentDirections.actionGameSimulatorFragmentToGameWinnerFragment()
                    findNavController().navigate(action)
                    Log.e("Winner team is :" , it[0][0].name)
                })
            } else {
                viewModel.simulateGame(currentTeams).observe(viewLifecycleOwner, Observer {
                    setupGames(it)
                    decideNavigationLabel(it.size)
                })
            }
        }
    }

    private fun prepareView() {
        binding.recyclerView2.adapter = adapter
    }

    private fun setupGames(games: List<List<IPLTeam>>) {
        currentTeams.clear()
        currentTeams.addAll(games)
        adapter.updateGames(games)
    }
}