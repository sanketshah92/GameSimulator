package com.sanket.iplsimulator.domain.usecases

import com.sanket.iplsimulator.data.models.IPLTeam
import com.sanket.iplsimulator.domain.repository.IPLTeamRepository

class SimulateGameUseCase(private val repository: IPLTeamRepository) {
    suspend fun execute(teams: List<List<IPLTeam>>) = repository.simulateGame(teams)
}