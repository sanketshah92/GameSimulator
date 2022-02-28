package com.sanket.iplsimulator.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sanket.iplsimulator.R
import com.sanket.iplsimulator.data.models.IPLTeam
import com.sanket.iplsimulator.databinding.ItemGameBinding

class GameSimulatorAdapter : RecyclerView.Adapter<GameSimulatorAdapter.ViewHolder>() {
    private val games = mutableListOf<List<IPLTeam>>()
    fun updateGames(updatedGames: List<List<IPLTeam>>) {
        games.clear()
        games.addAll(updatedGames)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemGameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(team1: String, team2: String) {
            binding.txtTeam1Name.text = team1
            binding.txtTeam2Name.text = team2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemGameBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_game, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position].first().name, games[position].last().name)
    }

    override fun getItemCount(): Int {
        return games.size
    }
}