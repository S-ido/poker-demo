package com.chebdowski.pokerdemo.domain

interface PokerRepository {

    suspend fun getRings(): Result<List<Ring>>
}