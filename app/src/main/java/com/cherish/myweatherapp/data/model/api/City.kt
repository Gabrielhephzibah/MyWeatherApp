package com.cherish.myweatherapp.data.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 class City(id: Int, name : String, country : String) {

    @SerializedName("id")

     var  id : Int? = null

    @SerializedName("name")

    var name : String? = null

    @SerializedName("country")

    var country :String? = null

    init {
        this.id = id
        this.name = name
        this.country = country
    }

     override fun toString(): String {
         return "City(id=$id, name=$name, country=$country)"
     }


 }