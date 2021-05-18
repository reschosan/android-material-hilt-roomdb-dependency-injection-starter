package com.example.myapplication.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ScreenInfo (
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "home") val home: String,
    @ColumnInfo(name = "dashboard") val dashboard: String,
    @ColumnInfo(name = "notifications") val notifications: String
        )