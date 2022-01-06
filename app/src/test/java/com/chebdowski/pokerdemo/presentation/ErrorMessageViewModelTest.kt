package com.chebdowski.pokerdemo.presentation

import com.chebdowski.pokerdemo.domain.Failure
import com.chebdowski.pokerdemo.interactors.GetErrorMessageUseCase
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ErrorMessageViewModelTest {

    private val failure = Failure.Unknown
    private val resourceId = 1

    @Mock
    private lateinit var getErrorMessageUseCase: GetErrorMessageUseCase

    @Test
    fun `getMessage should return correct resource id`() {
        `when`(getErrorMessageUseCase.invoke(failure)).thenReturn(resourceId)
        val errorMessageViewModel = ErrorMessageViewModel(getErrorMessageUseCase)

        val result = errorMessageViewModel.getMessage(failure)

        assertEquals(resourceId, result)
    }
}