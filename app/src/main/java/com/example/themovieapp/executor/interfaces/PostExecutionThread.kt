package com.example.themovieapp.executor.interfaces

import io.reactivex.Scheduler

interface PostExecutionThread {

    fun getScheduler():Scheduler

}