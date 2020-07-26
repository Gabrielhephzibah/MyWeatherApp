package com.cherish.myweatherapp.data.model.api

import com.cherish.myweatherapp.data.model.db.DataResponse

class ForecastResponse(response: DataResponse?, errorMessage: String) {
    var response : DataResponse? = null
    var errorMessage : String? = null
    init {
        this.response = response
        this.errorMessage = errorMessage
    }

    override fun toString(): String {
        return "ForecastResponse(response=$response, errorMessage=$errorMessage)"
    }




}