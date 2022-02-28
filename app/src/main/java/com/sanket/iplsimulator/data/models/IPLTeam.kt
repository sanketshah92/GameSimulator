package com.sanket.iplsimulator.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ipl_team")
data class IPLTeam(
    @PrimaryKey(autoGenerate = true)
    val id: Int, val name: String, val winCount: Int
)
