package com.chebdowski.pokerdemo.domain

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class GetRingsUseCaseTest {

    private val emptyListResult = Result.Success(listOf<Ring>())
    private val dummyRing = Ring("dummyName", "dummyGameType")
    private val ringsList = listOf(dummyRing)
    private val ringsListResult = Result.Success(ringsList)
    private val unknownErrorResult = Result.Error(Failure.Unknown)

    @Mock
    private lateinit var pokerRepository: PokerRepository

    @Test
    fun `getRings shouldReturn emptyList whenNoData`() = runTest {
        Mockito.`when`(pokerRepository.getRings()).thenReturn(emptyListResult)
        val result = pokerRepository.getRings()

        verify(pokerRepository).getRings()
        assertEquals(emptyListResult, result)
    }

    @Test
    fun `getRings shouldReturn correctElements`() = runTest {
        Mockito.`when`(pokerRepository.getRings()).thenReturn(ringsListResult)
        val result = pokerRepository.getRings()

        verify(pokerRepository).getRings()
        assertEquals(ringsListResult, result)
    }

    @Test
    fun `getRings shouldReturn correctError`() = runTest {
        Mockito.`when`(pokerRepository.getRings()).thenReturn(unknownErrorResult)
        val result = pokerRepository.getRings()

        verify(pokerRepository).getRings()
        assertEquals(unknownErrorResult, result)
    }
}