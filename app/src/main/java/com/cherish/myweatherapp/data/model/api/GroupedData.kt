package com.cherish.myweatherapp.data.model.api

import android.util.Log
import java.util.*
import java.util.Arrays.asList
import kotlin.collections.ArrayList

 class GroupedData @SafeVarargs constructor(vararg  args : List<Data>)  {

    private var dataGroup: ArrayList<List<Data>>? = null



      init {
        this.dataGroup = arrayListOf()
          this.dataGroup!!.addAll(args.toList())


    }


    fun addData(data: List<Data>) {
        if (dataGroup.isNullOrEmpty())
            dataGroup = java.util.ArrayList()
            dataGroup!!.add(data)

    }

    fun getDataList(): List<List<Data>>? {
        return dataGroup
    }



    override fun toString(): String {
        return "DataGroup{" +
                "dataGroup=" + dataGroup +
                '}'.toString()
    }
}


