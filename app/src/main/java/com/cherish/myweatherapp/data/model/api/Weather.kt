package com.cherish.myweatherapp.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather(id: Int, main: String, description:String, icon: String) {

    @SerializedName("id")
    @Expose
    var id :Int? = null

    @SerializedName("main")
    @Expose
    var main :String? = null

    @SerializedName("description")
    @Expose
    var description :String? = null

    @SerializedName("icon")
    @Expose
    var icon :String? = null

    init {
        this.id = id
        this.main = main
        this.description = description
        this.icon = icon
    }



}