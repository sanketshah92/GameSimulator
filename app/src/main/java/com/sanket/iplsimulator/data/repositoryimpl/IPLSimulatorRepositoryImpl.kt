package com.sanket.iplsimulator.data.repositoryimpl

import android.util.Log
import com.google.gson.Gson
import com.sanket.iplsimulator.data.datasource.IPLSimulatorLocalDataSource
import com.sanket.iplsimulator.data.models.IPLTeam
import com.sanket.iplsimulator.domain.repository.IPLTeamRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random

class IPLSimulatorRepositoryImpl(private val dataSource: IPLSimulatorLocalDataSource) :
    IPLTeamRepository {
    private val winners = mutableMapOf<Int, IPLTeam>()
    override suspend fun addIPLTeam(name: String): List<IPLTeam> {
        lateinit var updatedTeamList: List<IPLTeam>
        val newTeam = IPLTeam(0, name, 0)
        dataSource.addIPLTeam(newTeam)
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            updatedTeamList = dataSource.getIPLTeam()
        }
        return updatedTeamList
    }

    override suspend fun getIPLTeam(): List<IPLTeam> {
        lateinit var teams: List<IPLTeam>
        withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            teams = dataSource.getIPLTeam()
        }
        return teams
    }

    override suspend fun simulateGame(teams: List<List<IPLTeam>>): List<List<IPLTeam>> {
        return reduceTeams(teams)
    }

    private fun reduceTeams(teams: List<List<IPLTeam>>): List<List<IPLTeam>> {
        var tempTeam: IPLTeam
        val winnerTeams = mutableListOf<IPLTeam>()
        teams.forEach { group ->
            group.shuffled()
            val winnerIndex = Random.nextInt(group.size)
            winnerTeams.add(group[winnerIndex])
            tempTeam = if (winnerIndex > 0)
                group.first()
            else
                group.last()

            if (teams.size < 2) {
                winners[winnerTeams[0].id] = winnerTeams[0]
                winners[tempTeam.id] = tempTeam
            }
        }
        return createMatch(winnerTeams)
    }


    private fun createMatch(teams: List<IPLTeam>): List<List<IPLTeam>> {
        val teamGroups = mutableListOf<List<IPLTeam>>()
        val n: Int = teams.size
        teams.shuffled()
        if (n > 1) {
            for (i in 0 until n step 2) {
                if (i + 1 <= teams.size) {
                    teamGroups.add(teams.slice(IntRange(i, i + 1)))
                }
            }
        } else
            teamGroups.add(teams)
        return teamGroups
    }

    override suspend fun getPairsForGame(teams: List<IPLTeam>): List<List<IPLTeam>> {
        return createMatch(teams)
    }

    override suspend fun getWinners(): MutableMap<Int, IPLTeam> {
        return winners
    }
}