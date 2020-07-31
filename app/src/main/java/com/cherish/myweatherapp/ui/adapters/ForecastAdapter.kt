package com.cherish.myweatherapp.ui.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cherish.myweatherapp.R
import com.cherish.myweatherapp.data.model.api.Data
import com.cherish.myweatherapp.data.model.api.GroupedData
import com.cherish.myweatherapp.utils.AppUtils
import com.cherish.myweatherapp.utils.Constants
import com.cherish.myweatherapp.utils.GlideApp
import kotlinx.android.synthetic.main.weather_list_item.view.*

class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ForeCastViewHolder>() {

    lateinit var data: GroupedData
    init {
        this.data = GroupedData()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForeCastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_list_item,parent,false)
        return ForeCastViewHolder(view)
    }

    override fun getItemCount(): Int {

        Log.i("SIZEOFADAPTER", data.getDataList()!!.size.toString() )
        return data.getDataList()!!.size

    }

    override fun onBindViewHolder(holder: ForeCastViewHolder, position: Int) {
        Log.i("Bind data", "Bind Data")
        val datas : List<Data>  = data.getDataList()!!.get(position)
        holder.bind(datas)


    }

    fun  updateData(data : GroupedData){
        this.data = data
        notifyDataSetChanged()
    }

    class ForeCastViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val day = itemView.day
        val humidity = itemView.humidity
        val temperature = itemView.temperature
        val weatherImage = itemView.weatherImage
        val weatherCond = itemView.weather
        val weatherList = itemView.weatherList

        @SuppressLint("SetTextI18n")
        fun  bind(listData: List<Data>){
            Log.i("BIND FUNCTION", listData.toString())
            if (listData.size > 0){
                day.setText(AppUtils.getDay(listData.get(0).dtText.toString()))
                Log.i("Day",AppUtils.getDay(listData.get(0).dtText.toString()))
                weatherCond.setText(listData.get(0).weather?.get(0)?.main)
                Log.i("WeatherCond",listData.get(0).weather?.get(0)?.main)
                humidity.setText("${listData.get(0).main?.humidity}%")
                Log.i("Himidity","${listData.get(0).main?.humidity}%")
                temperature.setText("${listData.get(0).main?.temperature}°C")
                Log.i("Temperature","${listData.get(0).main?.temperature}°C")
                Log.i("IMAGE URL", "${Constants.IMAGE_URL}${listData.get(0).weather?.get(0)?.icon}.png")
                GlideApp.with(weatherImage)
                    .load("${Constants.IMAGE_URL}${listData.get(0).weather?.get(0)?.icon}.png")
                    .fitCenter()
                    .into(weatherImage)
                weatherList.layoutManager = LinearLayoutManager(weatherList.context,RecyclerView.HORIZONTAL,false)
                weatherList.setHasFixedSize(true)
                weatherList.adapter = WeatherAdapter(listData)


            }

        }


    }
}