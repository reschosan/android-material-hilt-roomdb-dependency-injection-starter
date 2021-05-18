package com.example.myapplication.db.helpers

import com.example.myapplication.db.AppDatabase
import com.example.myapplication.models.entities.ScreenInfo
import javax.inject.Inject

class ScreenInfoDBHelper @Inject constructor(private val appDatabase: AppDatabase) {

    fun getCurrentScreenInfo(): ScreenInfo {
        return appDatabase.screenInfoDao().getScreenInfo()
    }

    fun insertScreenInfo(screenInfo: ScreenInfo) {
        return appDatabase.screenInfoDao().insertScreenInfo(screenInfo)
    }

    fun updateScreenInfo(id: Int, home: String, dashBoard: String, notifications: String) {
        return appDatabase.screenInfoDao().updateScreenInfo(id, home, dashBoard, notifications)
    }
}