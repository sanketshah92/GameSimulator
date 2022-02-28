package com.sanket.iplsimulator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sanket.iplsimulator.data.models.IPLTeam
import com.sanket.iplsimulator.domain.usecases.*

class IPLSimulatorViewModel(
    private val addIPLTeamUseCase: AddIPLTeamUseCase,
    private val getIPLTeamsUseCase: GetIPLTeamsUseCase,
    private val simulateGameUseCase: SimulateGameUseCase,
    private val getTeamPairsUseCase: GetTeamPairsUseCase,
    private val getWinnersUseCase: GetWinnersUseCase
) : ViewModel() {

    fun addIPLTeam(name: String) = liveData {
        val result = addIPLTeamUseCase.execute(name)
        emit(result)
    }

    fun getIPLTeam() = liveData {
        val result = getIPLTeamsUseCase.execute()
        emit(result)
    }

    fun simulateGame(teams: List<List<IPLTeam>>) = liveData {
        val result = simulateGameUseCase.execute(teams)
        emit(result)
    }

    fun getTeamPairs(teams: List<IPLTeam>) = liveData {
        val result = getTeamPairsUseCase.execute(teams)
        emit(result)
    }

    fun getWinners() = liveData {
        val result = getWinnersUseCase.execute()
        emit(result)
    }

}