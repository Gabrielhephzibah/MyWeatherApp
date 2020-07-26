package com.cherish.myweatherapp.di.fragment_di

import androidx.lifecycle.ViewModelProvider
import com.cherish.myweatherapp.ViewModelProviderFactory
import com.cherish.myweatherapp.data.local.db.dao.CurrentDao
import com.cherish.myweatherapp.data.local.db.dao.ForeCastDao
import com.cherish.myweatherapp.data.remote.ApiService
import com.cherish.myweatherapp.data.remote.Repository
import com.cherish.myweatherapp.ui.MainActivityViewModel
import com.cherish.myweatherapp.utils.schedular.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class WeatherFragmentModule {
    @Provides
    fun  providesViewModel(repository: Repository, schedulerProvider: SchedulerProvider) : MainActivityViewModel{
        return MainActivityViewModel(repository,schedulerProvider)
    }

    @Provides
    fun provideRespository(apiService: ApiService,currentDao: CurrentDao,foreCastDao: ForeCastDao,schedulerProvider: SchedulerProvider): Repository{
        return  Repository(apiService,currentDao,foreCastDao,schedulerProvider)
    }

    @Provides
    fun provideViewModelProvider(mainActivityViewModel: MainActivityViewModel) : ViewModelProvider.Factory{
        return ViewModelProviderFactory(mainActivityViewModel)

    }

}