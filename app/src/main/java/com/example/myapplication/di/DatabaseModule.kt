package com.example.myapplication.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication.db.AppDatabase
import com.example.myapplication.db.helpers.ScreenInfoDBHelper
import com.example.myapplication.models.daos.ScreenInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideScreenInfoDao(appDatabase: AppDatabase): ScreenInfoDao {
        return appDatabase.screenInfoDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "ScreenInfoDB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideScreenInfoDBHelper(appDatabase: AppDatabase): ScreenInfoDBHelper {
        return ScreenInfoDBHelper(appDatabase)
    }
}