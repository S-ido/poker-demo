package com.chebdowski.pokerdemo.presentation.ringgames

import app.cash.turbine.test
import com.chebdowski.pokerdemo.domain.Failure
import com.chebdowski.pokerdemo.domain.Result
import com.chebdowski.pokerdemo.domain.Ring
import com.chebdowski.pokerdemo.interactors.GetRingsUseCase
import com.chebdowski.pokerdemo.presentation.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RingGamesViewModelTest {

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    private val dummyRing = Ring("dummyName", "dummyGameType", 0, 0)
    private val ringsList = listOf(dummyRing, dummyRing)
    private val ringsListResult = Result.Success(ringsList)
    private val errorResult = Result.Error(Failure.Unknown)

    @Mock
    private lateinit var getRingsUseCase: GetRingsUseCase<List<Ring>>

    @Test
    fun `ringGames flow should return correct data on successful result`() = runTest {
        `when`(getRingsUseCase()).thenReturn(ringsListResult)
        val ringGamesViewModel = RingGamesViewModel(getRingsUseCase)

        ringGamesViewModel.ringGames.test {
            assertEquals(ringsListResult, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `ringGames flow should return Failure on error result`() = runTest {
        `when`(getRingsUseCase()).thenReturn(errorResult)
        val ringGamesViewModel = RingGamesViewModel(getRingsUseCase)

        ringGamesViewModel.ringGames.test {
            assertEquals(errorResult, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}