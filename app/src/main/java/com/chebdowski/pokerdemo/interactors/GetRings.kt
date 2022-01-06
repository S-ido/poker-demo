package com.chebdowski.pokerdemo.interactors

import com.chebdowski.pokerdemo.domain.PokerRepository
import com.chebdowski.pokerdemo.domain.Ring

class GetRings(private val pokerRepository: PokerRepository) : GetRingsUseCase<List<Ring>> {

    override suspend fun invoke() = pokerRepository.getRings()
}