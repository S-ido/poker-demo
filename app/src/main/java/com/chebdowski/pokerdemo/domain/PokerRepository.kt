package com.chebdowski.pokerdemo.domain

interface PokerRepository {

    suspend fun getRings(): SafeResult<List<Ring>>
}