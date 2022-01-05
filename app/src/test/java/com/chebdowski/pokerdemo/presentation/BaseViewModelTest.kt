package com.chebdowski.pokerdemo.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.chebdowski.pokerdemo.domain.Failure
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class BaseViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `ViewModel should update LiveData on error`() {
        val testViewModel = TestViewModel()

        testViewModel.handleError(Failure.Unknown)

        val failure = testViewModel.failure
        val error = testViewModel.failure.value

        assertTrue(failure is MutableLiveData)
        assertTrue(error is Failure.Unknown)
    }

    private class TestViewModel : BaseViewModel() {
        fun handleError(failure: Failure) = handleFailure(failure)
    }
}