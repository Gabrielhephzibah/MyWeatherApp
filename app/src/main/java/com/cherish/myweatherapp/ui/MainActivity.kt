package com.cherish.myweatherapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.cherish.myweatherapp.R
import com.cherish.myweatherapp.ViewModelProviderFactory
import com.cherish.myweatherapp.fragment.WeatherFragment
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelProviderFactory
    var viewModel : MainActivityViewModel ? = null

    val weatherFragment = WeatherFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this,factory).get(MainActivityViewModel::class.java)

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentLayout, weatherFragment)
        transaction.commit()
//        transaction.addToBackStack(null)




    }
}
