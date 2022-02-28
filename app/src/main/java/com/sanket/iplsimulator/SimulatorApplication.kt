package com.sanket.iplsimulator

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.sanket.iplsimulator.data.db.GameSimulatorDB

class SimulatorApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }

    companion object {
        fun getDB(context: Context): GameSimulatorDB {
            return Room.databaseBuilder(context, GameSimulatorDB::class.java, "game_db").build()
        }
    }
}