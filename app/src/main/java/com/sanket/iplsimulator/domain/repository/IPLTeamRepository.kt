package com.sanket.iplsimulator.domain.repository

import com.sanket.iplsimulator.data.models.IPLTeam

interface IPLTeamRepository {
    suspend fun addIPLTeam(name: String): List<IPLTeam>
    suspend fun getIPLTeam(): List<IPLTeam>
    suspend fun simulateGame(teams: List<List<IPLTeam>>): List<List<IPLTeam>>
    suspend fun getPairsForGame(teams: List<IPLTeam>): List<List<IPLTeam>>
    suspend fun getWinners(): MutableMap<Int, IPLTeam>
}