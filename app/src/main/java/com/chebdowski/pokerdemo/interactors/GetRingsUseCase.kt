package com.chebdowski.pokerdemo.interactors

import com.chebdowski.pokerdemo.domain.PokerRepository

class GetRingsUseCase(private val pokerRepository: PokerRepository) {

    suspend operator fun invoke() = pokerRepository.getRings()
}