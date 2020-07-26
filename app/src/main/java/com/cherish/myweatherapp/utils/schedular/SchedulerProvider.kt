package com.cherish.myweatherapp.utils.schedular

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SchedulerProvider @Inject constructor() : BaseScheduler {
    override fun io(): Scheduler {
        return Schedulers.io()


    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()

    }

    override fun computation(): Scheduler {
        return Schedulers.computation()

    }




}