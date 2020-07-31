package com.cherish.myweatherapp.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather(id: Int, main: String, description:String, icon: String) {

    @SerializedName("id")
    var id :Int? = null

    @SerializedName("main")
    var main :String? = null

    @SerializedName("description")
    var description :String? = null

    @SerializedName("icon")
    var icon :String? = null

    init {
        this.id = id
        this.main = main
        this.description = description
        this.icon = icon
    }

    override fun toString(): String {
        return "Weather(id=$id, main=$main, description=$description, icon=$icon)"
    }


}