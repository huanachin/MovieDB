package com.example.themovieapp.executor

import com.example.themovieapp.executor.interfaces.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

import javax.inject.Inject
import javax.inject.Singleton

/**
 * MainThread (UI Thread) implementation based on a [Scheduler]
 * which will execute actions on the Android UI thread
 */
@Singleton
class UIThread @Inject
internal constructor() : PostExecutionThread {

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }


}