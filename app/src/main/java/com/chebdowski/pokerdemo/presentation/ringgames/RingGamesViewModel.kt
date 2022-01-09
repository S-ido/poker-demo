package com.chebdowski.pokerdemo.presentation.ringgames

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chebdowski.pokerdemo.domain.Result
import com.chebdowski.pokerdemo.domain.Ring
import com.chebdowski.pokerdemo.interactors.GetRingsUseCase
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

private const val FIVE_SECONDS = 5000L

class RingGamesViewModel(private val getRingsUseCase: GetRingsUseCase<List<Ring>>) : ViewModel() {

    val ringGames: StateFlow<Result<List<Ring>>> = flow {
//        emit(Result.Loading)
        emit(getRingsUseCase())
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribed(FIVE_SECONDS),
        initialValue = Result.Loading
    )
}