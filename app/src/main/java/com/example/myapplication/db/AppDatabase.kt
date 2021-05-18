package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.models.daos.ScreenInfoDao
import com.example.myapplication.models.entities.ScreenInfo

@Database(entities = [ScreenInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun screenInfoDao(): ScreenInfoDao
}