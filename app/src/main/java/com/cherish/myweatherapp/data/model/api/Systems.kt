package com.cherish.myweatherapp.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Systems(country: String, sunrise :Int, sunset :Int) {
    @SerializedName("country")
    @Expose
    private var country: String? = null

    @SerializedName("sunrise")
    @Expose
    private var sunrise: Int? = null


    @SerializedName("sunset")
    @Expose
    private var sunset: Int? = null

    init {
        this.country = country
        this.sunrise = sunrise
        this.sunset = sunset
    }





}