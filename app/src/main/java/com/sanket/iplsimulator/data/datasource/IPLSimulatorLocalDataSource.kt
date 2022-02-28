package com.sanket.iplsimulator.data.datasource

import com.sanket.iplsimulator.data.models.IPLTeam

interface IPLSimulatorLocalDataSource {
    suspend fun addIPLTeam(team: IPLTeam)
    suspend fun getIPLTeam(): List<IPLTeam>
}