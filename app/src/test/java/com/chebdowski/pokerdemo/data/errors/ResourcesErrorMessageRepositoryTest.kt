package com.chebdowski.pokerdemo.data.errors

import com.chebdowski.pokerdemo.R
import com.chebdowski.pokerdemo.domain.Failure
import org.junit.Assert.assertEquals
import org.junit.Test

class ResourcesErrorMessageRepositoryTest {

    private val errorMessageService = ResourceErrorMessageService()
    private val errorMessageRepository = ResourcesErrorMessageRepository(errorMessageService)

    @Test
    fun `getMessage should return correct resource id for Failure Http RequestTimeout`() {
        val resourceId = errorMessageRepository.getMessage(Failure.Http.RequestTimeout)

        assertEquals(R.string.error_request_timeout, resourceId)
    }

    @Test
    fun `getMessage should return correct resource id for Failure Http ServerError`() {
        val resourceId = errorMessageRepository.getMessage(Failure.Http.ServerError)

        assertEquals(R.string.error_request_server, resourceId)
    }

    @Test
    fun `getMessage should return correct resource id for Failure Http UnhandledError`() {
        val resourceId = errorMessageRepository.getMessage(Failure.Http.UnhandledError)

        assertEquals(R.string.error_request_general, resourceId)
    }

    @Test
    fun `getMessage should return correct resource id for Failure Unknown`() {
        val resourceId = errorMessageRepository.getMessage(Failure.Unknown)

        assertEquals(R.string.error_unknown, resourceId)
    }
}