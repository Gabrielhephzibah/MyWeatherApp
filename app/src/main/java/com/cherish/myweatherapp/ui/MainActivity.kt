package com.cherish.myweatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cherish.myweatherapp.R
import com.cherish.myweatherapp.fragment.WeatherFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : DaggerAppCompatActivity()  {
    val weatherFragment = WeatherFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentLayout, weatherFragment)
        transaction.commit()
//        transaction.addToBackStack(null)




    }
}
