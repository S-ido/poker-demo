package com.chebdowski.pokerdemo.presentation.ringgames

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chebdowski.pokerdemo.domain.Ring
import com.chebdowski.pokerdemo.interactors.GetRingsUseCase
import com.chebdowski.pokerdemo.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RingGamesViewModel(private val getRingsUseCase: GetRingsUseCase) : BaseViewModel() {

    private val _ringGames: MutableLiveData<List<Ring>> = MutableLiveData()
    val ringGames: LiveData<List<Ring>> = _ringGames

    fun loadRingGames() {
        setLoading(true)

        viewModelScope.launch(Dispatchers.IO) {
            val response = getRingsUseCase()

            withContext(Dispatchers.Main) {
                response.fold(
                    success = { handleRingGames(it) },
                    error = { handleFailure(it) }
                )
            }
        }
    }

    private fun handleRingGames(ringGames: List<Ring>) {
        _ringGames.value = ringGames
        setLoading(false)
    }
}