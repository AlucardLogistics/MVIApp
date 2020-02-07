package com.alucardlogistics.mviapp.util

import androidx.lifecycle.LiveData

// a live data class that has 'null' value
class AbsentLiveData<T: Any?> private constructor(): LiveData<T>() {

    init {
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}