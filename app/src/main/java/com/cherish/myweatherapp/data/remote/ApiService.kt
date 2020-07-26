package com.cherish.myweatherapp.data.remote

import com.cherish.myweatherapp.data.model.db.CurrentWeatherResponse
import com.cherish.myweatherapp.data.model.db.DataResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    fun getCurrentWeather(@Query("q")q : String,
                          @Query("units")unit : String,
                          @Query("APPID")key :String ) : Single<Response<CurrentWeatherResponse>>


    @GET("weather")
    fun getCurrentWeatherByLatAndLog(@Query("lat")lat : Double,
                          @Query("long")long: Double,
                          @Query("units")unit :String,
                            @Query("APPID") key: String ) : Single<Response<CurrentWeatherResponse>>


    @GET("forecast")
    fun getForeCastByCity(@Query("q") q: String,
                            @Query("units") unit: String,
                          @Query("APPID")key: String) : Single<Response<DataResponse>>

    @GET("forecast")
    fun getForecastByLatAndLong(@Query("lat") lat: Double,
                                @Query ("long") long: Double,
                               @Query("units") unit: String,
                               @Query("APPID") key: String) : Single<Response<DataResponse>>


}