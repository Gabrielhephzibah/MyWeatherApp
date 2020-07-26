package com.cherish.myweatherapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cherish.myweatherapp.data.local.db.dao.CurrentDao
import com.cherish.myweatherapp.data.local.db.dao.ForeCastDao
import com.cherish.myweatherapp.data.model.db.CurrentWeatherResponse
import com.cherish.myweatherapp.data.model.db.DataResponse

@Database(entities = [DataResponse::class,CurrentWeatherResponse::class],version = 0)
@TypeConverters(Converters::class)
abstract  class AppDataBase : RoomDatabase() {
   abstract val getCurrentDao : CurrentDao

    abstract val getForecastDao : ForeCastDao
}