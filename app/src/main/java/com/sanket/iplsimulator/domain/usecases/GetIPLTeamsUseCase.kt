package com.sanket.iplsimulator.domain.usecases

import com.sanket.iplsimulator.domain.repository.IPLTeamRepository

class GetIPLTeamsUseCase(private val repository: IPLTeamRepository) {
    suspend fun execute() = repository.getIPLTeam()
}