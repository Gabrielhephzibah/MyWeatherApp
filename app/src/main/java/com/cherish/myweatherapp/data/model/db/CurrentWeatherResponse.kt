package com.cherish.myweatherapp.data.model.db

import android.opengl.Visibility
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cherish.myweatherapp.data.local.db.Converters
import com.cherish.myweatherapp.data.model.api.*
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "current_weather")
class CurrentWeatherResponse {

    @PrimaryKey
    @NotNull
    var idd : Int = 1

   @SerializedName("weather")
//   @TypeConverters(Converters::class)
   var weather : List<Weather> ? = null


    @SerializedName("main")
//    @TypeConverters(Converters::class)
    var main : Main? = null


    @SerializedName("visibility")
    var visibility : Int? = null


    @SerializedName("wind")
//    @TypeConverters(Converters::class)
    var wind : Wind? = null


    @SerializedName("clouds")
//    @TypeConverters(Converters::class)
    var cloud : Cloud? = null


    @SerializedName("dt")
    var date : Int ? = null


    @SerializedName("id")
    var id : Int ? = null


    @SerializedName("name")
    var name : String ? = null


    @SerializedName("cod")
    var cod : Int? = null


    @SerializedName("sys")
//    @TypeConverters(Converters::class)
    var systems : Systems? = null


    init {
       this.id = id
       this.weather = weather
        this.main = main
        this.visibility = visibility
        this.wind = wind
        this.cloud = cloud
        this.date = date
        this.idd = idd
        this.name = name
        this.cod = cod
        this.systems = systems


    }

    override fun toString(): String {
        return "CurrentWeather(id=$id, weather=$weather, main=$main, visibility=$visibility, wind=$wind, cloud=$cloud, dt=$date, idd=$idd, name=$name, cod=$cod, system=$systems)"
    }





}