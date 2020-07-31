package com.cherish.myweatherapp.data.model.db

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.cherish.myweatherapp.data.local.db.Converters
import com.cherish.myweatherapp.data.model.api.City
import com.cherish.myweatherapp.data.model.api.Data
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity(tableName = "weather_forecast")
class DataResponse(id : Int, name :String, cod :String, ctn : Int, country: String, list : List<Data>, city: City ) {

    @PrimaryKey
    @NotNull
    var id  : Int = 1


    var  name : String? = null

    @SerializedName("cod")
    var  cod : String? = null

    @SerializedName("country")
    var  country : String? = null


    @SerializedName("cnt")
    var  cnt : Int? = null

//    @TypeConverters(Converters::class)
    @SerializedName("list")
    var  list : List<Data>? = null

//    @TypeConverters(Converters::class)
    @SerializedName("city")
    var  city : City? = null




    init {
        this.id = id
        this.name = name
        this.cod = cod
        this.cnt = ctn
        this.country = country
        this.list = list
        this.city = city
    }

    constructor():this(1,"","",0,"", mutableListOf<Data>(),City(0," ",""))

    override fun toString(): String {
        return "DataResponse(id=$id, name=$name, cod=$cod, country=$country, cnt=$cnt, list=$list, city=$city)"
    }


}