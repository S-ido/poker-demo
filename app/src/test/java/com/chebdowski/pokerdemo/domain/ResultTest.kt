package com.chebdowski.pokerdemo.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class ResultTest {

    @Test
    fun `successful result should return correct data`() {
        val dummyRingsList = listOf<Ring>()
        val result = Result.Success(dummyRingsList)

        assertEquals(dummyRingsList, result.data)
    }

    @Test
    fun `error result should return Failure`() {
        val result = Result.Error(Failure.Unknown)

        assertEquals(Failure.Unknown, result.failure)
    }
}