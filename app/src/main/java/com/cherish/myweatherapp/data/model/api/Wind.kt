package com.cherish.myweatherapp.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Wind(speed : Double, degree: Double) {
    @SerializedName("speed")
    var speed :Double? = null


    @SerializedName("deg")
    var degree :Double? = null


    init {
        this.speed = speed
        this.degree = degree
    }

    override fun toString(): String {
        return "Wind(speed=$speed, degree=$degree)"
    }


}