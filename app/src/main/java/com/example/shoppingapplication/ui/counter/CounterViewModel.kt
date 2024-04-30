package com.example.shoppingapplication.ui.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    // This should be private
//    var counterValue: Int = 0

    private val _counter = MutableLiveData<Int>(0)
    val counter: LiveData<Int> = _counter

    fun updateCounter(change: Int) {
        if (_counter.value?.plus(change)!! >= 0)
            _counter.postValue(_counter.value?.plus(change))
    }

    fun resetCounter() = _counter.postValue(0)

    fun getCounterValue() = counter.value ?: 0
}