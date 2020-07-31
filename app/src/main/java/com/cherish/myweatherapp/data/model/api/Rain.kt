package com.cherish.myweatherapp.data.model.api

import com.google.gson.annotations.SerializedName

class Rain(rainValue: Double) {
    @SerializedName("3h")
    var rainValue: Double? = null

    init {
        this.rainValue = rainValue
    }

    override fun toString(): String {
        return "Rain(rainValue=$rainValue)"
    }

}