package com.chebdowski.pokerdemo.presentation.ringgames

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chebdowski.pokerdemo.domain.Failure
import com.chebdowski.pokerdemo.domain.Result
import com.chebdowski.pokerdemo.domain.Ring
import com.chebdowski.pokerdemo.interactors.GetRingsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
    var instantExecutorRule = InstantTaskExecutorRule()

    private val dummyRing = Ring("dummyName", "dummyGameType")
    private val ringsList = listOf(dummyRing, dummyRing)
    private val ringsListResult = Result.Success(ringsList)
    private val errorResult = Result.Error(Failure.Unknown)
    private val testDispatcher = Dispatchers.Unconfined

    @Mock
    private lateinit var getRingsUseCase: GetRingsUseCase<List<Ring>>

    @Test
    fun `loadRingGames should update ringGames LiveData when successful result`() = runTest {
        `when`(getRingsUseCase()).thenReturn(ringsListResult)
        val ringGamesViewModel = RingGamesViewModel(getRingsUseCase, testDispatcher, testDispatcher)

        ringGamesViewModel.loadRingGames()
        val result = ringGamesViewModel.ringGames.value

        assertNotNull(result)
        assertEquals(ringsList, result)
    }

    @Test
    fun `loadRingGames should update failure's LiveData when error result`() = runTest {
        `when`(getRingsUseCase()).thenReturn(errorResult)
        val ringGamesViewModel = RingGamesViewModel(getRingsUseCase, testDispatcher, testDispatcher)

        ringGamesViewModel.loadRingGames()
        val result = ringGamesViewModel.failure.value

        assertNotNull(result)
        assertEquals(Failure.Unknown, result)
    }
}