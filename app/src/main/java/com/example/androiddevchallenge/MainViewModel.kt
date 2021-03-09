/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {
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
