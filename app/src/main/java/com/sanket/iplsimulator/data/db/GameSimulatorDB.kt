package com.sanket.iplsimulator.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sanket.iplsimulator.data.models.IPLTeam

@Database(
    entities = [IPLTeam::class], version = 1,
    exportSchema = false
)
abstract class GameSimulatorDB : RoomDatabase() {
    abstract fun getDAO(): IPLSimulatorDAO

}