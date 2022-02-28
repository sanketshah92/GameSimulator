package com.sanket.iplsimulator.domain.usecases

import com.sanket.iplsimulator.domain.repository.IPLTeamRepository

class AddIPLTeamUseCase(private val repository: IPLTeamRepository) {
    suspend fun execute(name: String) = repository.addIPLTeam(name)
}