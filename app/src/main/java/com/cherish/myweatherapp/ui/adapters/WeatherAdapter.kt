package com.cherish.myweatherapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cherish.myweatherapp.R
import com.cherish.myweatherapp.data.model.api.Data
import com.cherish.myweatherapp.utils.AppUtils
import com.cherish.myweatherapp.utils.Constants
import com.cherish.myweatherapp.utils.GlideApp
import kotlinx.android.synthetic.main.weather_attribute_item_list.view.*

class WeatherAdapter(data: List<Data>) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

   lateinit var  data : List<Data>

    init {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weather_attribute_item_list,parent,false)
        return WeatherViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  data.size

    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(data.get(position))

    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val forcastTime  = itemView.forecastTime
        val forecastTemp  = itemView.forecastTemp
        var forecastImage  = itemView.forecastImage


        @SuppressLint("SetTextI18n")
        fun bind(data: Data){
            forecastTemp.setText("${data.main?.temperature} C")
            GlideApp.with(forecastImage)
                .load(" ${Constants.IMAGE_URL+data.weather?.get(0)?.icon}.png")
                .fitCenter()
                .into(forecastImage)
            forcastTime.setText(AppUtils.getTimeString(data.dtText.toString()))


        }


    }
}