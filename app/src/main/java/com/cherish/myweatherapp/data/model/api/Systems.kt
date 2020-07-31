package com.cherish.myweatherapp.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Systems(country: String, sunrise :Int, sunset :Int) {
    @SerializedName("country")
     var country: String? = null

    @SerializedName("sunrise")
    var sunrise: Int? = null

    @SerializedName("sunset")
     var sunset: Int? = null

    init {
        this.country = country
        this.sunrise = sunrise
        this.sunset = sunset
    }

    override fun toString(): String {
        return "Systems(country=$country, sunrise=$sunrise, sunset=$sunset)"
    }


}