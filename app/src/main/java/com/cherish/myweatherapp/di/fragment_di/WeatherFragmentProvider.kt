package com.cherish.myweatherapp.di.fragment_di

import com.cherish.myweatherapp.fragment.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class WeatherFragmentProvider {
    @ContributesAndroidInjector(modules = [WeatherFragmentModule::class])

    abstract fun bindMainFragment(): WeatherFragment
}