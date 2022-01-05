package com.chebdowski.pokerdemo.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chebdowski.pokerdemo.domain.Failure
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class BaseViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val testViewModel = TestViewModel()

    @Test
    fun `handleFailure should update failure's LiveData`() {
        testViewModel.handleError(Failure.Unknown)

        val error = testViewModel.failure.value

        assertTrue(error is Failure.Unknown)
    }

    @Test
    fun `setLoading should update loading's LiveData`() {
        testViewModel.handleLoading(true)

        val loadingState = testViewModel.loading.value

        assertNotNull(loadingState)
        assertTrue(loadingState!!)
    }

    @Test
    fun `handleFailure should set loading to false`() {
        testViewModel.handleLoading(true)
        testViewModel.handleError(Failure.Unknown)

        val loadingState = testViewModel.loading.value

        assertNotNull(loadingState)
        assertFalse(loadingState!!)
    }

    private class TestViewModel : BaseViewModel() {
        fun handleError(failure: Failure) = handleFailure(failure)
        fun handleLoading(loading: Boolean) = setLoading(loading)
    }
}