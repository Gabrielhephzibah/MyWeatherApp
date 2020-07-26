package com.cherish.myweatherapp.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main(temperature :Double, pressure :Double, humidity :Double, tempMin :Double, tempMax :Double) {
    @SerializedName("temp")
    @Expose
    var temperature : Double? = null


    @SerializedName("pressure")
    @Expose
    var pressure :Double? = null

    @SerializedName("humidity")
    @Expose
    var humidity :Double? = null

    @SerializedName("temp_min")
    @Expose
    var tempMin : Double? = null

    @SerializedName("temp_max")
    @Expose
    var tempMax :Double? = null

    init {
        this.temperature = temperature
        this.pressure = pressure
        this.humidity = humidity
        this.tempMin = tempMin
        this.tempMax = tempMax
    }

}