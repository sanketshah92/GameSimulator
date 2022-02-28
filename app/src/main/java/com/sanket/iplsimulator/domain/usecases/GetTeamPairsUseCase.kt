package com.sanket.iplsimulator.domain.usecases

import com.sanket.iplsimulator.data.models.IPLTeam
import com.sanket.iplsimulator.domain.repository.IPLTeamRepository

class GetTeamPairsUseCase(private val repository: IPLTeamRepository) {
    suspend fun execute(teams: List<IPLTeam>) = repository.getPairsForGame(teams)
}