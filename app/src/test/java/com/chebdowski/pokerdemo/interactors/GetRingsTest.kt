package com.chebdowski.pokerdemo.interactors

import com.chebdowski.pokerdemo.domain.Failure
import com.chebdowski.pokerdemo.domain.PokerRepository
import com.chebdowski.pokerdemo.domain.Result
import com.chebdowski.pokerdemo.domain.Ring
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
class GetRingsTest {

    private val emptyListResult = Result.Success(listOf<Ring>())
    private val dummyRing = Ring("dummyName", "dummyGameType", 0, 0)
    private val ringsList = listOf(dummyRing, dummyRing)
    private val ringsListResult = Result.Success(ringsList)
    private val unknownErrorResult = Result.Error(Failure.Unknown)

    @Mock
    private lateinit var pokerRepository: PokerRepository

    @Test
    fun `getRingsUseCase should return empty list when no data`() = runTest {
        Mockito.`when`(pokerRepository.getRings()).thenReturn(emptyListResult)
        val getRingsUseCase = GetRings(pokerRepository)

        val result = getRingsUseCase()

        verify(pokerRepository).getRings()
        assertEquals(emptyListResult, result)
    }

    @Test
    fun `getRingsUseCase should return correct elements`() = runTest {
        Mockito.`when`(pokerRepository.getRings()).thenReturn(ringsListResult)
        val getRingsUseCase = GetRings(pokerRepository)

        val result = getRingsUseCase()

        verify(pokerRepository).getRings()
        assertEquals(ringsListResult, result)
        assertEquals(ringsList.size, ringsListResult.data.size)
    }

    @Test
    fun `getRingsUseCase should return correct error`() = runTest {
        Mockito.`when`(pokerRepository.getRings()).thenReturn(unknownErrorResult)
        val getRingsUseCase = GetRings(pokerRepository)

        val result = getRingsUseCase()

        verify(pokerRepository).getRings()
        assertEquals(unknownErrorResult, result)
        assertEquals(Failure.Unknown, (result as Result.Error).failure)
    }
}