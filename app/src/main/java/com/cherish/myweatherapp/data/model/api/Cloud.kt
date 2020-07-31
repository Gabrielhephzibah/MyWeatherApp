package com.cherish.myweatherapp.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Cloud(cloudAll : Int) {

    @SerializedName("all")

    var cloudAll :Int? = null

    init {
        this.cloudAll = cloudAll
    }

    override fun toString(): String {
        return "Cloud(cloudAll=$cloudAll)"
    }


}