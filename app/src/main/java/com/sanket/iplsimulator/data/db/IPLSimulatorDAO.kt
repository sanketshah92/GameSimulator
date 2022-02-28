package com.sanket.iplsimulator.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sanket.iplsimulator.data.models.IPLTeam

@Dao
interface IPLSimulatorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIPLTeam(team: IPLTeam)

    @Query("SELECT * FROM ipl_team")
    suspend fun getIPLTeams(): List<IPLTeam>
}