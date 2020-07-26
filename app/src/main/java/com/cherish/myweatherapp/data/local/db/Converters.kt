package com.cherish.myweatherapp.data.local.db

import androidx.room.TypeConverter
import com.cherish.myweatherapp.data.model.api.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromClouds(cloud: Cloud) : String {
        return Gson().toJson(cloud)
    }

    @TypeConverter
    fun toCloud(string: String):Cloud {
        return Gson().fromJson(string, Cloud::class.java)
    }

    @TypeConverter
    fun fromSystems(system: Systems):String{
        return Gson().toJson(system)
    }

    @TypeConverter
    fun toSystems(string: String):Systems{
        return Gson().fromJson(string, Systems::class.java)
    }

    @TypeConverter
    fun fromWinds(wind: Wind):String{
        return Gson().toJson(wind)
    }

    @TypeConverter
    fun toWinds(string: String):Wind{
        return Gson().fromJson(string, Wind::class.java)
    }

    @TypeConverter
   fun fromMain(main: Main):String{
        return Gson().toJson(main)

    }

    @TypeConverter
    fun toMain(string: String):Main{
        return  Gson().fromJson(string,Main::class.java)
    }

    @TypeConverter
    fun fromRain(rain: Rain) :String{
        return Gson().toJson(rain)
    }

    @TypeConverter
    fun toRain(string: String):Rain{
        return Gson().fromJson(string, Rain::class.java)
    }

    @TypeConverter
    fun fromList(list: List<Weather>) : String {
        return Gson().toJson(list)

    }

    @TypeConverter
    fun toList(string: String) : List<Weather>{
        val type = object:TypeToken<List<Weather>>(){}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun fromData(data: List<Data>): String{
        return Gson().toJson(data)

    }

    @TypeConverter
    fun toData(string: String): List<Data>{
        val type = object : TypeToken<List<Data>>(){}.type
        return Gson().fromJson(string, type)

    }

    @TypeConverter
    fun  fromCity(city: City):String{
        return  Gson().toJson(city)
    }

    @TypeConverter
    fun toCity(string: String):City{
        return Gson().fromJson(string,City::class.java)
    }



    }