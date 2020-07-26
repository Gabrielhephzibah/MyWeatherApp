package com.cherish.myweatherapp.data.model.api

import java.util.*
import kotlin.collections.ArrayList

class GroupedData(vararg  arg : List<Data>)  {
    public var dataGroup : ArrayList<List<Data>>? = null

    init {
        this.dataGroup = ArrayList()
        this.dataGroup!!.addAll(Arrays.asList(*arg))
    }

    fun addData(data :List<Data>){
        if (dataGroup==null){
            dataGroup = ArrayList()
            dataGroup!!.add(data)
        }
    }

    override fun toString(): String {
        return "GroupedData(dataGroup=$dataGroup)"
    }


}