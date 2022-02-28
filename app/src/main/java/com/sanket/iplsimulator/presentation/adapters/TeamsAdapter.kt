package com.sanket.iplsimulator.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sanket.iplsimulator.R
import com.sanket.iplsimulator.data.models.IPLTeam
import com.sanket.iplsimulator.databinding.ItemIplTeamBinding

class TeamsAdapter : RecyclerView.Adapter<TeamsAdapter.ViewHolder>() {
    private val teams = mutableListOf<IPLTeam>()
    fun updateTeams(updatedTeams: List<IPLTeam>) {
        teams.clear()
        teams.addAll(updatedTeams)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemIplTeamBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(team: IPLTeam) {
            binding.txtTeamName.text = team.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemIplTeamBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_ipl_team, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(teams[position])
    }

    override fun getItemCount(): Int {
        return teams.size
    }
}