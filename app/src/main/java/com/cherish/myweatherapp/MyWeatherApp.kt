package com.cherish.myweatherapp

import com.cherish.myweatherapp.di.component.AppComponent
import com.cherish.myweatherapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class MyWeatherApp  :DaggerApplication()  {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
       return DaggerAppComponent.builder().create(this)

    }



}