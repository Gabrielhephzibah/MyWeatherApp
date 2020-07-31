package com.cherish.myweatherapp.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Main(temperature :Double, pressure :Double, humidity :Double, tempMin :Double, tempMax :Double) {
    @SerializedName("temp")
    var temperature : Double? = null


    @SerializedName("pressure")
    var pressure :Double? = null

    @SerializedName("humidity")
    var humidity :Double? = null

    @SerializedName("temp_min")
    var tempMin : Double? = null

    @SerializedName("temp_max")
    var tempMax :Double? = null

    init {
        this.temperature = temperature
        this.pressure = pressure
        this.humidity = humidity
        this.tempMin = tempMin
        this.tempMax = tempMax
    }

    override fun toString(): String {
        return "Main(temperature=$temperature, pressure=$pressure, humidity=$humidity, tempMin=$tempMin, tempMax=$tempMax)"
    }


}