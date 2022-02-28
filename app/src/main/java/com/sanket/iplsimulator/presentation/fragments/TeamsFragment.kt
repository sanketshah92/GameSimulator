package com.sanket.iplsimulator.presentation.fragments

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.sanket.iplsimulator.MainActivity
import com.sanket.iplsimulator.R
import com.sanket.iplsimulator.data.models.IPLTeam
import com.sanket.iplsimulator.databinding.FragmentStartIplBinding
import com.sanket.iplsimulator.presentation.IPLSimulatorViewModel
import com.sanket.iplsimulator.presentation.adapters.TeamsAdapter
import kotlinx.parcelize.Parcelize

class TeamsFragment : Fragment(), OnNewTeamsAdded {
    private lateinit var binding: FragmentStartIplBinding
    private lateinit var viewModel: IPLSimulatorViewModel
    private val adapter = TeamsAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start_ipl, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_team, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView()
        prepareViewModel()
        getTeams()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_team -> {
                showAddTeam()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }


    private fun prepareView() {
        binding.recyclerView.adapter = adapter

    }

    private fun prepareViewModel() {
        viewModel = (activity as MainActivity).viewModel
    }

    private fun getTeams() {
        if (::viewModel.isInitialized) {
            viewModel.getIPLTeam().observe(viewLifecycleOwner, Observer {
                adapter.updateTeams(it)
                manageStartIPL(it)
            })
        }
    }

    private fun manageStartIPL(teams: List<IPLTeam>) {

        binding.button.setOnClickListener {
            if (teams.isNotEmpty() && teams.size % 2 == 0) {
                val action = TeamsFragmentDirections.actionTeamsFragmentToGameSimulatorFragment()
                findNavController().navigate(action)
            } else {
                Snackbar.make(binding.button, "Please add team to simulate", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun showAddTeam() {

        val addDialogFragment = AddTeamDialogFragment(this)
        addDialogFragment.show(childFragmentManager, "addDialog")
    }

    override fun onTeamsAdded(teams: List<IPLTeam>) {
        adapter.updateTeams(teams)
        manageStartIPL(teams)
    }
}

interface OnNewTeamsAdded {
    fun onTeamsAdded(teams: List<IPLTeam>)
}
