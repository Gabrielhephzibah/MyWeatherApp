package com.cherish.myweatherapp.ui.adapters

import android.annotation.SuppressLint
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

class ForecastAdapter(dataGroup: GroupedData  = GroupedData()) : RecyclerView.Adapter<ForecastAdapter.ForeCastViewHolder>() {

    lateinit var dataGroup: GroupedData
    init {
        this.dataGroup = dataGroup
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForeCastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_list_item,parent,false)
        return ForeCastViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataGroup.dataGroup!!.size

    }

    override fun onBindViewHolder(holder: ForeCastViewHolder, position: Int) {
        val data : List<Data> = dataGroup.dataGroup!!.get(position)
        holder.bind(data)


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
            if (listData.size < 0){
                day.setText(AppUtils.getDay(listData.get(0).dtText.toString()))
                weatherCond.setText(listData.get(0).weather?.get(0)?.main)
                humidity.setText("${listData.get(0).main?.humidity}%")
                temperature.setText("${listData.get(0).main?.temperature}°C")
                GlideApp.with(weatherImage)
                    .load(" ${Constants.IMAGE_URL+listData.get(0).weather?.get(0)?.icon}.png")
                    .fitCenter()
                    .into(weatherImage)
                weatherList.layoutManager = LinearLayoutManager(weatherList.context,RecyclerView.HORIZONTAL,false)
                weatherList.setHasFixedSize(true)
                weatherList.adapter = WeatherAdapter(listData)


            }

        }


    }
}