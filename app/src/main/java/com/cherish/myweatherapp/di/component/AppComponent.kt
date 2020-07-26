package com.cherish.myweatherapp.di.component

import com.cherish.myweatherapp.MyWeatherApp
import com.cherish.myweatherapp.di.builder.ActivityBuilder
import com.cherish.myweatherapp.di.modules.AppModule
import com.cherish.myweatherapp.di.modules.ContextModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Component (modules = [AndroidSupportInjectionModule::class, ContextModule::class, AppModule::class,ActivityBuilder::class])
interface AppComponent : AndroidInjector<MyWeatherApp> {
     override fun inject(application: MyWeatherApp)

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyWeatherApp>()

}