package com.cherish.myweatherapp.di.builder

import com.cherish.myweatherapp.di.fragment_di.WeatherFragmentProvider
import com.cherish.myweatherapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [WeatherFragmentProvider::class])

   abstract fun bindMainActivity(): MainActivity


}