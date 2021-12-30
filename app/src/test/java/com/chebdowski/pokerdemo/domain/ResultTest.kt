package com.chebdowski.pokerdemo.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class ResultTest {

    @Test
    fun `successResult shouldReturn CorrectData`() {
        val dummyRingsList = listOf<Ring>()
        val result = Result.Success(dummyRingsList)

        assertEquals(dummyRingsList, result.data)
    }

    @Test
    fun `errorResult shouldReturn Failure`() {
        val result = Result.Error(Failure.Unknown)

        assertEquals(Failure.Unknown, result.error)
    }
}