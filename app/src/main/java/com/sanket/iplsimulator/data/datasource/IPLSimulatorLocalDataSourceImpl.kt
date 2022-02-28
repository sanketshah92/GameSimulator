package com.sanket.iplsimulator.data.datasource

import com.sanket.iplsimulator.data.db.IPLSimulatorDAO
import com.sanket.iplsimulator.data.models.IPLTeam
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IPLSimulatorLocalDataSourceImpl(private val iplSimulatorDAO: IPLSimulatorDAO) :
    IPLSimulatorLocalDataSource {
    override suspend fun addIPLTeam(team: IPLTeam) {
        CoroutineScope(Dispatchers.IO).launch {
            iplSimulatorDAO.addIPLTeam(team)
        }
    }

    override suspend fun getIPLTeam(): List<IPLTeam> = iplSimulatorDAO.getIPLTeams()


}