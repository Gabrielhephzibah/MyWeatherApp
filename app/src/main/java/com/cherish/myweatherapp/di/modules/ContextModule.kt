package com.cherish.myweatherapp.di.modules

import android.content.Context
import com.cherish.myweatherapp.MyWeatherApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule {

    @Singleton
    @Provides
    fun provideContext(myWeatherApp: MyWeatherApp): Context{
        return  myWeatherApp
    }


}