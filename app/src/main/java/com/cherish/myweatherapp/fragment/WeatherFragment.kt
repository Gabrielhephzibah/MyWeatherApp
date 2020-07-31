package com.cherish.myweatherapp.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.opengl.Visibility
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cherish.myweatherapp.R
import com.cherish.myweatherapp.ViewModelProviderFactory
import com.cherish.myweatherapp.data.model.api.Data
import com.cherish.myweatherapp.data.model.api.GroupedData
import com.cherish.myweatherapp.data.model.db.CurrentWeatherResponse
import com.cherish.myweatherapp.data.model.db.DataResponse
import com.cherish.myweatherapp.ui.MainActivityViewModel
import com.cherish.myweatherapp.ui.adapters.ForecastAdapter
import com.cherish.myweatherapp.utils.AppUtils
import com.cherish.myweatherapp.utils.Constants
import com.cherish.myweatherapp.utils.GlideApp
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.weather_fragment_layout.*
import kotlinx.android.synthetic.main.weather_fragment_layout.view.*
import kotlinx.android.synthetic.main.weather_list_item.view.*
import java.lang.Exception
import java.util.*
import java.util.jar.Manifest
import javax.inject.Inject
import kotlin.collections.ArrayList

class WeatherFragment : DaggerFragment() {
    val LOCATION_REQUEST = 100


    @Inject
   lateinit var factory: ViewModelProvider.Factory
    lateinit var viewModel: MainActivityViewModel
    var fusedLocationProviderClient: FusedLocationProviderClient? = null
    lateinit var editSearch: EditText
    lateinit var progressBarOne: ProgressBar
    lateinit var progressBarTwo: ProgressBar
    lateinit var currentLayout: LinearLayout
    lateinit var todayDate: TextView
    lateinit var location: TextView
    lateinit var currentTemp: TextView
    lateinit var currentHumd: TextView
    lateinit var currentWind: TextView
    lateinit var currentPressure: TextView
    lateinit var currentIndoor: TextView
    lateinit var weatherListItem: RecyclerView
    lateinit var tempCond: TextView
    lateinit var minMaxTemp: TextView
    lateinit var weatherImage: ImageView
    var forecastAdapter : ForecastAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (activity != null) {
            fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(activity!!)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.weather_fragment_layout, container, false)
        viewModel = ViewModelProviders.of(this, factory).get(MainActivityViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
          initializeView(view)
        intializeCall()
    }

    override fun onResume() {
        super.onResume()
        intializeCall()
    }


    fun initializeView(view: View) {
        editSearch = view.editSearch
        progressBarOne = view.progressBarOne
        progressBarTwo = view.progressBarTwo
        currentLayout = view.currentLayout
        todayDate = view.todayDate
        location = view.location
        currentTemp = view.currentTemp
        currentHumd = view.currentHumd
        currentWind = view.windSpeed
        currentPressure = view.pressure
        currentIndoor = view.indoorTemp
        weatherListItem = view.weather_item_list
        tempCond = view.tempCond
        minMaxTemp = view.minMaxTemp
        weatherImage = view.currentWeatherImage

        editSearch.setOnEditorActionListener { v, actionId, event ->
            if (!TextUtils.isEmpty(editSearch.text)) {
                fetchData(editSearch.text.toString())
                hideKeyboard()
                true
            } else {
                false
            }
        }

        forecastAdapter = ForecastAdapter()
         weatherListItem.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        weatherListItem.setHasFixedSize(true)
        weatherListItem.addItemDecoration(DividerItemDecoration(context,RecyclerView.VERTICAL))
        weatherListItem.adapter = forecastAdapter


    }

    fun fetchData(cityName: String) {
        progressBarOne.visibility = View.VISIBLE
        progressBarTwo.visibility = View.VISIBLE
        weatherListItem.visibility = View.GONE
        currentLayout.visibility = View.GONE
        viewModel.getCurrentDate(cityName).observe(this, Observer { response ->
            showCurrent(response.currentWeatherResponse!!, response.errorMessage!!)

        })

        viewModel.getForeCast(cityName).observe(this, Observer { response ->
            showForecast(response.response!!, response.errorMessage!!)

        })



    }

    fun showCurrent(response: CurrentWeatherResponse, error: String) {
        if (response != null) {
            todayDate.setText(AppUtils.getDateString(response.date!!.toLong()))
            location.setText("${response.name}, ${response.systems?.country}")
            currentTemp.setText("${response.main?.temperature}°")
            currentHumd.setText("${response.main?.humidity}%")
            currentIndoor.setText("${response.main?.tempMax}°C")
            currentWind.setText("${response.wind?.speed}m/s")
            currentPressure.setText("${response.main?.pressure?.div(1000)} bar")
            tempCond.setText("${response.weather?.get(0)?.main}")
            minMaxTemp.setText("${response.main?.tempMin}°C/" + "${response.main?.tempMax}°C")
            Log.i("IMAGE URL", "${Constants.IMAGE_URL}${response.weather!!.get(0).icon}.png")
            GlideApp.with(weatherImage)
                .load("${Constants.IMAGE_URL}${response.weather!!.get(0).icon}.png")
                .fitCenter()
                .into(weatherImage)
            progressBarOne.visibility = View.GONE
            currentLayout.visibility = View.VISIBLE

        }
        if (!error.isEmpty()) {
            progressBarOne.visibility = View.GONE
            currentLayout.visibility = View.GONE
            AppUtils.showToast(context!!, error)
        }

    }

    fun showForecast(response: DataResponse, error: String) {
        if (response != null) {
            Log.i("dataResponse", response.toString())
            val dataOne = java.util.ArrayList<Data>()
            val dataTwo = java.util.ArrayList<Data>()
            val dataThree = java.util.ArrayList<Data>()
            val dataFour = java.util.ArrayList<Data>()
            val dataFive = java.util.ArrayList<Data>()
            val dataSix = java.util.ArrayList<Data>()

            val calender0 = Calendar.getInstance()
            calender0.set(Calendar.HOUR_OF_DAY, 0)
            calender0.set(Calendar.MINUTE, 0)
            calender0.set(Calendar.SECOND, 0)
            calender0.set(Calendar.MILLISECOND, 0)
            val calender1 = calender0.clone() as Calendar
            calender1.add(Calendar.DAY_OF_YEAR, 1)
            val calender2 = calender0.clone() as Calendar
            calender2.add(Calendar.DAY_OF_YEAR, 2)
            val calender3 = calender0.clone() as Calendar
            calender3.add(Calendar.DAY_OF_YEAR, 3)
            val calender4 = calender0.clone() as Calendar
            calender4.add(Calendar.DAY_OF_YEAR, 4)
            val calender5 = calender0.clone() as Calendar
            calender5.add(Calendar.DAY_OF_YEAR, 5)


            for (datas in response.list!!.iterator()) {

//            for (data:Data in response.list!!){
                Log.i("Datassssss", datas.toString())
                when {
                    getCalenderFromDate(datas.dt!!).before(calender1) -> dataOne.add(datas)
                    getCalenderFromDate(datas.dt!!).before(calender2) -> dataTwo.add(datas)
                    getCalenderFromDate(datas.dt!!).before(calender3) -> dataThree.add(datas)
                    getCalenderFromDate(datas.dt!!).before(calender4) -> dataFour.add(datas)
                    getCalenderFromDate(datas.dt!!).before(calender5) -> dataFive.add(datas)
                    else -> dataSix.add(datas)
                }

                Log.i("DATAONE", dataOne.toString())
                Log.i("DATATwo", dataTwo.toString())
                Log.i("DATAThree", dataThree.toString())
                Log.i("DATA4", dataFour.toString())
                Log.i("DATA5", dataFive.toString())
                Log.i("DATA6", dataSix.toString())

            }

            val groupedData: GroupedData = GroupedData(dataOne)
                if (dataTwo.size > 0){
                    Log.i("data0", dataTwo.toString())
                     groupedData.addData(dataTwo)
                }
              if (dataThree.size > 0){
                  groupedData.addData(dataThree)
              }

                if (dataFour.size > 0){
                    groupedData.addData(dataFour)
                }
            if (dataFive.size > 0 ){
                groupedData.addData(dataFive)
            }
            if (dataSix.size > 0 ){
                groupedData.addData(dataSix)
            }

            Log.i("GRouped DAta", groupedData.toString())
            Log.i("Show forecast","Show forecast")
              forecastAdapter!!.updateData(groupedData)
              progressBarTwo.visibility = View.GONE
              weatherListItem.visibility = View.VISIBLE
        }
        if (!error.isEmpty()){
            progressBarTwo.visibility = View.GONE
            weatherListItem.visibility = View.GONE
            AppUtils.showToast(context!!, error)
        }

    }


    fun getCalenderFromDate(date:Long):Calendar {
      val calendar:Calendar = Calendar.getInstance()
        calendar.timeInMillis = date * 1000L
        return  calendar

    }


    fun hideKeyboard() {
        if (activity!=null){
           val inputMethodManager : InputMethodManager =  activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            try {
                  inputMethodManager.hideSoftInputFromWindow(view?.windowToken,0)

            }catch (e: Exception){
                  e.printStackTrace()
            }
        }
    }

    fun fetchedDataFromLocation(location: Location){
        progressBarTwo.visibility = View.VISIBLE
        weatherListItem.visibility  = View.GONE
        viewModel.getForecastGeo(location.latitude, location.longitude).observe(this, Observer {response->
          showForecast(response.response!!, response.errorMessage!!)
        })
          progressBarOne.visibility = View.VISIBLE
           currentLayout.visibility = View.GONE
       viewModel.getCurrentDateGeo(location.latitude, location.longitude).observe(this, Observer { response->
              showCurrent(response.currentWeatherResponse!!, response.errorMessage!!)
       })

    }

    fun checkForPermission() : Boolean{
       return (ContextCompat.checkSelfPermission(context!!, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context!!, android.Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED )
    }

    fun requestPermission() {
        if (!checkForPermission()) {
            if (activity!=null){
               if (ActivityCompat.shouldShowRequestPermissionRationale(activity!!,android.Manifest.permission.ACCESS_FINE_LOCATION))  {

               }else{
                  ActivityCompat.requestPermissions(activity!!, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION ),LOCATION_REQUEST)
               }
            }

        }
    }

    override fun onRequestPermissionsResult( requestCode: Int,permissions: Array<out String>, grantResults: IntArray ) {
        if (grantResults.isNotEmpty()){
            if (requestCode == LOCATION_REQUEST && grantResults[0]==PackageManager.PERMISSION_GRANTED ) {
                 if (activity!=null){
                     fromLocation()

                 }
            }else {
                AppUtils.showToast(context!!, "Location not active${fetchData("Ajah NG")}")
            }
        } else {

            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


    fun fromLocation() {
        if (checkForPermission()) {
            fusedLocationProviderClient?.lastLocation!!.addOnSuccessListener { location ->
               if (location != null){
                   Log.i("LOCATION SEEN", "LOCATION SEEN")
                    fetchedDataFromLocation(location)
               }else{
                   fetchData("Ikeja")
                   Log.i("LOCATION NOT SEEN", "LOCATION NOT SEEN")
               }
            }
        }  else{
             AppUtils.showToast(context!!, "No Internet Connection")
        }

    }

    @SuppressLint("CheckResult")
    fun intializeCall(){
        if (checkForPermission())  {
            AppUtils.hasInternet().subscribe({connected->
              if (connected){
               if (activity!=null) {
                   Log.i("Connected status", "IS CONNECTED")
                   fromLocation()

               }
              } else{
                   fetchData(" ")
                  Log.i("Connected status", "BAD CONNECTED")
              }
            }, { error ->
                AppUtils.showToast(context!!,"No Network and no data saved")
            })
        } else{
            requestPermission()
        }
    }


}