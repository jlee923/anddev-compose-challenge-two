package com.example.androiddevchallenge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainViewModel: ViewModel() {
    var startTime by mutableStateOf("")
        private set
    var time by mutableStateOf("")
        private set
    private var currentTime: Long = 0L
    private var countdown: Disposable? = null

    fun updateTime(seconds: String) {
        countdown?.dispose()
        time = seconds
        startTime = seconds
        currentTime = seconds.toLong()
    }

    // onNameChange is an event we're defining that the UI can invoke
    // (events flow up from UI)
    fun startClock() {
        countdown?.dispose()
        currentTime = startTime.toLong()
        time = currentTime.toString()
        countdown = Flowable.interval(1, TimeUnit.SECONDS)
            .map {
                currentTime--
            }
            .takeUntil {
                currentTime == 0L
            }
            .subscribeOn(Schedulers.io())
            .subscribe {
                if (currentTime >= 0) {
                    time = currentTime.toString()
                }
            }
    }

    fun stopClock() {
        countdown?.dispose()
    }
}