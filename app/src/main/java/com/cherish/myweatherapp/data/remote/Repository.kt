package com.cherish.myweatherapp.data.remote

import com.cherish.myweatherapp.BuildConfig
import com.cherish.myweatherapp.data.local.db.dao.CurrentDao
import com.cherish.myweatherapp.data.local.db.dao.ForeCastDao
import com.cherish.myweatherapp.data.model.db.CurrentWeatherResponse
import com.cherish.myweatherapp.data.model.db.DataResponse
import com.cherish.myweatherapp.utils.schedular.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject


class Repository @Inject constructor(apiService: ApiService,currentDao: CurrentDao,foreCastDao: ForeCastDao, schedulerProvider: SchedulerProvider) {
    lateinit var  apiService: ApiService
    lateinit var currentDao: CurrentDao
    lateinit var foreCastDao: ForeCastDao
    lateinit var schedulerProvider : SchedulerProvider




    init {
       this.apiService = apiService
       this.currentDao = currentDao
       this.foreCastDao = foreCastDao
        this.schedulerProvider = schedulerProvider
   }

    fun getForecast(cityName: String): Single<retrofit2.Response<DataResponse>>{
    return apiService.getForeCastByCity(cityName,"metric", BuildConfig.APPID)
    }

    fun getFoecastGeo(lat:Double, long :Double):Single<retrofit2.Response<DataResponse>>{
        return apiService.getForecastByLatAndLong(lat,long,"metric", BuildConfig.APPID)
    }

    fun getWeather(cityName: String) :Single<Response<CurrentWeatherResponse>>{
        return apiService.getCurrentWeather(cityName,"metric",BuildConfig.APPID)
    }

    fun getweatherGeo(lat : Double, long : Double) : Single<Response<CurrentWeatherResponse>>{
        return  apiService.getCurrentWeatherByLatAndLog(lat,long ,"metric", BuildConfig.APPID)
    }


    /**
     * Data from and to offline storage
     */

    fun currentFromDb() : Single<CurrentWeatherResponse>{
        return currentDao.getCurrent()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.io())
    }

    fun forcastFromDb() : Single<DataResponse>{
        return foreCastDao.getDataById()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.io())
    }

    fun insertCurrentToDb(response : CurrentWeatherResponse) : Completable{
        return currentDao.insertCurrent(response)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.io())
    }

    fun insertForcastToDb(response: DataResponse) :Completable{
        return foreCastDao.insertForecastData(response)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.io())
    }








}