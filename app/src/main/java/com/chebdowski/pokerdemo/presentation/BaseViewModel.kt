package com.chebdowski.pokerdemo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chebdowski.pokerdemo.domain.Failure

abstract class BaseViewModel : ViewModel() {

    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    val failure: LiveData<Failure> = _failure

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    protected fun handleFailure(failure: Failure) {
        _failure.value = failure
        setLoading(false)
    }

    protected fun setLoading(loading: Boolean) {
        _loading.value = loading
    }
}