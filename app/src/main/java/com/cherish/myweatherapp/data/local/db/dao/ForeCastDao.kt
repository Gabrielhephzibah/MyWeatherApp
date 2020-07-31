package com.cherish.myweatherapp.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cherish.myweatherapp.data.model.db.DataResponse
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ForeCastDao {
    @Query("select * from weather_forecast where id = 1")
    fun  getDataById(): Single<DataResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecastData(response: DataResponse):Completable
//    fun insertForcastData(response: Response): Completable

}