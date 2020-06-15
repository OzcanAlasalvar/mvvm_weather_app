package com.mvvm.weatherapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mvvm.weatherapp.data.model.CityModel

@Database(entities = [CityModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao
}