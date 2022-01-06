package com.chebdowski.pokerdemo.interactors

import com.chebdowski.pokerdemo.domain.ErrorMessageRepository
import com.chebdowski.pokerdemo.domain.Failure
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetErrorMessageTest {

    @Mock
    private lateinit var errorMessageRepository: ErrorMessageRepository

    private val fakeErrorMessageResourceId = 1

    @Test
    fun `getMessageUseCase should return error message resource id`() {
        `when`(errorMessageRepository.getMessage(Failure.Unknown)).thenReturn(fakeErrorMessageResourceId)
        val getErrorMessageUseCase = GetErrorMessage(errorMessageRepository)

        val resourceId = getErrorMessageUseCase(Failure.Unknown)

        assertEquals(fakeErrorMessageResourceId, resourceId)
    }
}