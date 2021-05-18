package com.example.myapplication.models.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.models.entities.ScreenInfo

@Dao
interface ScreenInfoDao {

    @Query("select * from screeninfo limit 1")
    fun getScreenInfo(): ScreenInfo

    @Insert
    fun insertScreenInfo(screenInfo: ScreenInfo)

    @Query("update screeninfo set home = :home, dashboard = :dash, notifications = :notifications where uid = :id")
    fun updateScreenInfo(id: Int, home: String, dash: String, notifications: String)
}