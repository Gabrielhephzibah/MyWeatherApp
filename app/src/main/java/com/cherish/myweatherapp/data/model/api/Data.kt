package com.cherish.myweatherapp.data.model.api

import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Data( dt :Long, main: Main, weather: List<Weather>, cloud: Cloud,wind: Wind,rain: Rain,  dtText: String ) {
    @PrimaryKey
    var id :Int ? = null

    @SerializedName("dt")
    var dt : Long? = null

    @SerializedName("main")
    var main : Main? = null

    @SerializedName("weather")
    var weather : List<Weather>? = null

    @SerializedName("clouds")
    var cloud : Cloud? = null

    @SerializedName("wind")
    var wind : Wind? = null

    @SerializedName("rain")
    var rain : Rain? = null

    @SerializedName("dt_txt")
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

    override fun toString(): String {
        return "Data(id=$id, dt=$dt, main=$main, weather=$weather, cloud=$cloud, wind=$wind, rain=$rain, dtText=$dtText)"
    }


}