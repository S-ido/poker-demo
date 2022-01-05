package com.chebdowski.pokerdemo.interactors

import com.chebdowski.pokerdemo.domain.PokerRepository
import com.chebdowski.pokerdemo.domain.Ring

class GetRingsUseCase(private val pokerRepository: PokerRepository) : UseCase<List<Ring>> {

    override suspend fun invoke() = pokerRepository.getRings()
}