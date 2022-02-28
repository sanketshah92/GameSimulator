package com.sanket.iplsimulator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sanket.iplsimulator.domain.usecases.*

class IPLSimulatorViewModelFactory(
    private val addIPLTeamUseCase: AddIPLTeamUseCase,
    private val getIPLTeamsUseCase: GetIPLTeamsUseCase,
    private val simulateGameUseCase: SimulateGameUseCase,
    private val getTeamPairsUseCase: GetTeamPairsUseCase,
    private val getWinnersUseCase: GetWinnersUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return IPLSimulatorViewModel(
            addIPLTeamUseCase,
            getIPLTeamsUseCase,
            simulateGameUseCase,
            getTeamPairsUseCase,
            getWinnersUseCase
        ) as T
    }
}