package com.cherish.myweatherapp.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 class City(id: Int, name : String, country : String) {

    @SerializedName("id")
    @Expose
     var  id : Int? = null

    @SerializedName("name")
    @Expose
    var name : String? = null

    @SerializedName("country")
    @Expose
    var country :String? = null

    init {
        this.id = id
        this.name = name
        this.country = country
    }

}