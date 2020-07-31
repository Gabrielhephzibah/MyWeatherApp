package com.cherish.myweatherapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cherish.myweatherapp.data.model.db.CurrentWeatherResponse
import io.reactivex.Completable
import io.reactivex.Single
import java.nio.channels.SelectableChannel
import javax.inject.Singleton

@Dao
interface CurrentDao {
    @Query("select * from current_weather where idd = 1")
    fun  getCurrent(): Single<CurrentWeatherResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrent(currentWeatherR: CurrentWeatherResponse): Completable






}