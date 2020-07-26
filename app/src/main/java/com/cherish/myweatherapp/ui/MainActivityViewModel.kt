package com.cherish.myweatherapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cherish.myweatherapp.data.model.api.ForecastResponse
import com.cherish.myweatherapp.data.model.api.WeatherResponse
import com.cherish.myweatherapp.data.model.db.DataResponse
import com.cherish.myweatherapp.data.remote.Repository
import com.cherish.myweatherapp.utils.schedular.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(repository: Repository, schedulerProvider: SchedulerProvider) : ViewModel() {
    lateinit var repository : Repository
    lateinit var schedulerProvider: SchedulerProvider

    private var weatherMutableLiveData : MutableLiveData<WeatherResponse> = MutableLiveData()

    private var forecastMutableLiveData : MutableLiveData<ForecastResponse> = MutableLiveData()

    private var geoWeatherMutableLiveData : MutableLiveData<WeatherResponse> = MutableLiveData()

    private var geoForecastMutableLiveData : MutableLiveData<ForecastResponse> = MutableLiveData()

    var  disposable : CompositeDisposable = CompositeDisposable()

    init {
        this.repository = repository
        this.schedulerProvider = schedulerProvider
    }


 fun getForecasts(cityName: String){
     disposable.add(repository.getForecast(cityName)
         .subscribeOn(schedulerProvider.io())
         .observeOn(schedulerProvider.ui())
         .subscribe({ response->
             if (response.isSuccessful) {
                 if (response.body()!=null){
                     response.body()!!.name = response.body()!!.city!!.name
                     disposable.add(repository.insertForcastToDb(response.body()!!)
                         .subscribeOn(schedulerProvider.io())
                         .observeOn(schedulerProvider.io())
                         .subscribe({
                             forecastMutableLiveData.postValue(
                                 ForecastResponse(
                                     response.body()!!, ""
                                 )
                             )
                         }, { error ->
                             forecastMutableLiveData.postValue(
                                 ForecastResponse(
                                     response.body()!!, "Failed to save to DB"
                                 )
                             )
                         }))
                 }else if (response.errorBody()!=null){
                     forecastMutableLiveData.postValue(ForecastResponse(null,response.message()))
                 }
             }

         }, { error ->
             if (error is SocketTimeoutException || error is ConnectException || error is UnknownHostException){
                 forecastMutableLiveData.postValue(ForecastResponse(null,"Poor internet"))
             }else{
               forecastMutableLiveData.postValue(ForecastResponse(null,"An unknown error occurred"))
             }
         }
         ))

 }


    fun getForcastGeo(lat: Double, long: Double) {
        disposable.add(repository.getFoecastGeo(lat,long)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({response->
                if (response.isSuccessful){
                    if (response.body()!=null){
                       if (response.body()!!.city!=null) {
                           response.body()!!.name = response.body()!!.city?.name
                           if (repository.insertForcastToDb(response.body()!!)!=null){
                               disposable.add(repository.insertForcastToDb(response.body()!!)
                                   .subscribeOn(schedulerProvider.io())
                                   .observeOn(schedulerProvider.io())
                                   .subscribe({geoForecastMutableLiveData.postValue(ForecastResponse(response.body(),""))},
                                       { error -> geoForecastMutableLiveData.postValue(
                                           ForecastResponse(response.body(),"failed to save to DB")
                                       )}))

                               }

                       }
                    }
                }else if (response.errorBody()!=null){
                    geoForecastMutableLiveData.postValue(ForecastResponse(null,response.message()))
                }


            },{ error ->
                if (error is SocketTimeoutException || error is ConnectException || error is UnknownHostException){
                    geoForecastMutableLiveData.postValue(ForecastResponse(null,"Poor internet"))
                }else{
                    geoForecastMutableLiveData.postValue(ForecastResponse(null,"An unknown error occurred"))
                }
            }))
    }


    fun getWeathers(cityName: String){
        disposable.add(repository.getWeather(cityName)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({response->
                if (response.isSuccessful && response.body()!=null){
                   disposable.add(repository.insertCurrentToDb(response.body()!!)
                       .subscribeOn(schedulerProvider.io())
                       .observeOn(schedulerProvider.io())
                       .subscribe({
                           weatherMutableLiveData.postValue(WeatherResponse(response.body()!!,""))
                       }, { error ->
                           weatherMutableLiveData.postValue(WeatherResponse(response.body()!!,"Error occurred! unable to save to database"))
                       }))
                } else if (response.body()!= null){
                    weatherMutableLiveData.postValue(WeatherResponse(null, response.message()))
                }
            }, { error ->

                if (error is SocketTimeoutException || error is ConnectException || error is UnknownHostException){
                    weatherMutableLiveData.postValue(WeatherResponse(null,"Poor internet"))
                }else{
                    weatherMutableLiveData.postValue(WeatherResponse(null,"An unknown error occurred"))
                }
            }))

    }


    fun  getWeatherGeo(lat: Double, long: Double){
        disposable.add(repository.getweatherGeo(lat,long)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({response->
                if (response.isSuccessful && response.body()!=null){
                    disposable.add(repository.insertCurrentToDb(response.body()!!)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.io())
                        .subscribe({
                            geoWeatherMutableLiveData.postValue(WeatherResponse(response.body(),""))
                        }, { error ->
                            geoWeatherMutableLiveData.postValue(WeatherResponse(response.body(), "failed to save to Db"))
                        }))
                } else if (response.errorBody()!= null){
                    geoWeatherMutableLiveData.postValue(WeatherResponse(null, response.message()))
                }
            }, { error ->

                if (error is SocketTimeoutException || error is ConnectException || error is UnknownHostException){
                    geoWeatherMutableLiveData.postValue(WeatherResponse(null,"Poor internet"))
                }else{
                    geoWeatherMutableLiveData.postValue(WeatherResponse(null,"An unknown error occurred"))
                }
            }))

    }




    fun getWeatherFromDb(cityName: String){
        disposable.add(repository.currentFromDb()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.io())
            .subscribe({response->
                if (response!=null && cityName == ""){
                    weatherMutableLiveData.postValue(WeatherResponse(response,""))
                }else{
                    getWeathers(cityName)
                }
            },{ error->
                error.printStackTrace()
                getWeathers(cityName)
            }))
    }



    fun getForecastFromDb(cityName: String) {
        disposable.add(
            repository.forcastFromDb()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.io())
                .subscribe({ currentResponse ->
                    if (currentResponse != null && cityName == "") {
                        forecastMutableLiveData.postValue(ForecastResponse(currentResponse, ""))
                    } else {
                        getForecasts(cityName)
                    }
                }, { error ->
                    error.printStackTrace()
                    getForecasts(cityName)
                })
        )
    }


    fun getForeCast(cityName: String): LiveData<ForecastResponse>{
       getForecastFromDb(cityName)
        return forecastMutableLiveData
    }

    fun getCurrentDate(cityName: String) : LiveData<WeatherResponse>{
        getWeatherFromDb(cityName)
        return weatherMutableLiveData
    }

    fun getForecastGeo(lat:Double,long:Double): LiveData<ForecastResponse>{
        getForecastGeo(lat,long)
        return geoForecastMutableLiveData
    }

    fun getCurrentDateGeo(lat: Double,long: Double): LiveData<WeatherResponse>{
        getWeatherGeo(lat,long)
        return geoWeatherMutableLiveData
    }


    fun  getForecastInDb(): DataResponse {
        return repository.forcastFromDb().blockingGet()
    }


}