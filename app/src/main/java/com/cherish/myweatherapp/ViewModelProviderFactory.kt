package com.cherish.myweatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class ViewModelProviderFactory<V : Any>(viewModel: V) : ViewModelProvider.Factory {
     var viewModel: V ? = null

    init {
        this.viewModel = viewModel
    }
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModel!!.javaClass)) {
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }


//    class ViewModelFactory<V>(private val viewModel: V) : ViewModelProvider.Factory {
//
//
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(viewModel.javaClass)) {
//                return viewModel as T
//            }
//            throw IllegalArgumentException("Unknown class name")
//        }
//
//    }
}