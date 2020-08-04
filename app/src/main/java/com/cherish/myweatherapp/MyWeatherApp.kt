package com.cherish.myweatherapp

import android.app.Activity
import android.app.Application
import com.cherish.myweatherapp.di.component.AppComponent
import com.cherish.myweatherapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyWeatherApp  : DaggerApplication() {

//    @Inject
//    lateinit var activityDispatchingAndroidInjector : DispatchingAndroidInjector<Activity>
//
//    override fun activityInjector(): AndroidInjector<Activity> {
//       return activityDispatchingAndroidInjector
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        DaggerAppComponent.builder()
//            .
//
//    }



//
//    override fun activityInjector(): AndroidInjector<Activity> {
//
//    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
       return DaggerAppComponent.builder().create(this)

    }



}