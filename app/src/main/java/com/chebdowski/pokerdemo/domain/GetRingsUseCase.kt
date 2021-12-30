package com.chebdowski.pokerdemo.domain

suspend inline fun getRings(pokerRepository: PokerRepository) = pokerRepository.getRings()

typealias GetRingsUseCase = () -> SafeResult<List<Ring>>