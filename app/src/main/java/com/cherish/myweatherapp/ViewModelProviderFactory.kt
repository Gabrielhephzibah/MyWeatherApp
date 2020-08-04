package com.cherish.myweatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cherish.myweatherapp.data.remote.Repository
import com.cherish.myweatherapp.ui.MainActivityViewModel
import com.cherish.myweatherapp.utils.schedular.SchedulerProvider
import javax.inject.Inject

class ViewModelProviderFactory @Inject constructor(repository: Repository, schedulerProvider: SchedulerProvider) : ViewModelProvider.Factory {

    var repository: Repository? = null

    var schedulerProvider : SchedulerProvider ? = null

    init {
        this.repository = repository
        this.schedulerProvider = schedulerProvider
    }


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(repository!!,schedulerProvider!!) as T
        }
        throw IllegalArgumentException("Unknown class name")

    }

}