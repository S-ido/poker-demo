package com.chebdowski.pokerdemo.domain

interface PokerRepository {

    /**
     * A suspended function for obtaining a list of ring games.
     * @return Either [Result.Success] with a list of [Ring] or a [Result.Error] with a proper [Failure] reason.
     */
    suspend fun getRings(): Result<List<Ring>>
}