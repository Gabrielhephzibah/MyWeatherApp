package com.cherish.myweatherapp.data.model.api

import com.cherish.myweatherapp.data.model.db.CurrentWeatherResponse

class WeatherResponse(currentWeatherResponse: CurrentWeatherResponse?, errorMessage: String) {

     var currentWeatherResponse :CurrentWeatherResponse? = null
    var errorMessage : String? = null

    init {
        this.currentWeatherResponse = currentWeatherResponse
        this.errorMessage = errorMessage

    }

    override fun toString(): String {
        return "WeatherResponse(currentWeatherResponse=$currentWeatherResponse, errorMessage=$errorMessage)"
    }


}