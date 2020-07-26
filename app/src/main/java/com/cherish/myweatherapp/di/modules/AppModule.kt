package com.cherish.myweatherapp.di.modules

import android.content.Context
import androidx.room.Room
import com.cherish.myweatherapp.BuildConfig
import com.cherish.myweatherapp.data.local.db.AppDataBase
import com.cherish.myweatherapp.data.local.db.dao.CurrentDao
import com.cherish.myweatherapp.data.local.db.dao.ForeCastDao
import com.cherish.myweatherapp.data.remote.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {
     lateinit var  okhttpclient :OkHttpClient

    @Singleton
    @Provides
    fun providesClient(loggingInterceptor : HttpLoggingInterceptor) : OkHttpClient{
        val REQUEST_TIMEOUT = 15
        okhttpclient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
        return okhttpclient

    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor{
        val httpLoggingInterceptor  = HttpLoggingInterceptor()
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return  httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun  provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return  Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    }

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDataBase{
        return Room.databaseBuilder(context, AppDataBase::class.java, "my_weather_app")
            .fallbackToDestructiveMigration()
            .build()

    }


    @Singleton
    @Provides
    fun provideCurrentDao(appDataBase: AppDataBase): CurrentDao{
        return  appDataBase.getCurrentDao
    }

    @Singleton
    @Provides
    fun provideForcastDao(appDataBase: AppDataBase):ForeCastDao{
        return  appDataBase.getForecastDao
    }

    @Singleton
    @Provides

    fun  provideRerofitService(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }















}