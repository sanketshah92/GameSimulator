package com.sanket.iplsimulator.presentation.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sanket.iplsimulator.MainActivity
import com.sanket.iplsimulator.R
import com.sanket.iplsimulator.data.models.IPLTeam
import com.sanket.iplsimulator.databinding.DialogAddTeamBinding
import com.sanket.iplsimulator.presentation.IPLSimulatorViewModel
import com.sanket.iplsimulator.presentation.validators.ValidationUtils

class AddTeamDialogFragment(private val onNewTeamsAdded: OnNewTeamsAdded) : DialogFragment() {
    private lateinit var binding: DialogAddTeamBinding
    private lateinit var viewModel: IPLSimulatorViewModel
    private val validator = ValidationUtils()
    private lateinit var newTeams: MutableList<IPLTeam>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_team, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(70)
        viewModel = (activity as MainActivity).viewModel
        prepareView()
    }

    private fun prepareView() {
        binding.buttonAdd.setOnClickListener {
            val team1Name = binding.edtTeam1Name.text.toString()
            val team2Name = binding.edtTeam2Name.text.toString()
            val isInputValid = validator.validateTwoTeams(
                team1Name,
                team2Name
            )
            if (isInputValid) {
                viewModel.addIPLTeam(team1Name).observe(viewLifecycleOwner, Observer {
                    viewModel.addIPLTeam(team2Name).observe(viewLifecycleOwner, Observer {
                        newTeams = mutableListOf()
                        newTeams.addAll(it)
                        dismiss()
                    })
                })
            } else {
                Toast.makeText(context, "Please add both team names..", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (::newTeams.isInitialized)
            onNewTeamsAdded.onTeamsAdded(newTeams)
    }

    private fun DialogFragment.setWidthPercent(percentage: Int) {
        val percent = percentage.toFloat() / 100
        val dm = Resources.getSystem().displayMetrics
        val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
        val percentWidth = rect.width() * percent
        dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}