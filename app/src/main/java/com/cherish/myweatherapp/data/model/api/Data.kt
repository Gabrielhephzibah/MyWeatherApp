package com.cherish.myweatherapp.data.model.api

import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data( dt :Int, main: Main, weather: List<Weather>, cloud: Cloud,wind: Wind,rain: Rain,  dtText: String ) {
    @PrimaryKey
    var id :Int ? = null

    @SerializedName("dt")
    @Expose
    var dt : Int? = null

    @SerializedName("main")
    @Expose
    var main : Main? = null

    @SerializedName("weather")
    @Expose
    var weather : List<Weather>? = null

    @SerializedName("clouds")
    @Expose
    var cloud : Cloud? = null

    @SerializedName("wind")
    @Expose
    var wind : Wind? = null

    @SerializedName("rain")
    @Expose
    var rain : Rain? = null

    @SerializedName("dt_txt")
    @Expose
    var dtText : String? = null

    init {
        this.id = id
        this.dt = dt
        this.main = main
        this.weather = weather
        this.cloud = cloud
        this.wind = wind
        this.rain = rain
        this.dtText = dtText
    }



}