package com.cherish.myweatherapp.utils.schedular

import io.reactivex.Scheduler

interface BaseScheduler {
    abstract fun io(): Scheduler
    fun ui() : Scheduler
    fun computation() :Scheduler
}