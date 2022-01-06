package com.chebdowski.pokerdemo.presentation

import androidx.lifecycle.ViewModel
import com.chebdowski.pokerdemo.domain.Failure
import com.chebdowski.pokerdemo.interactors.GetErrorMessage
import com.chebdowski.pokerdemo.interactors.GetErrorMessageUseCase

class ErrorMessageViewModel(private val getErrorMessageUseCase: GetErrorMessageUseCase) : ViewModel() {

    fun getMessage(failure: Failure) = getErrorMessageUseCase(failure)
}