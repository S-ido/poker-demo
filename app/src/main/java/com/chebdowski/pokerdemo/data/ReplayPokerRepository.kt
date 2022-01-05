package com.chebdowski.pokerdemo.data

import com.chebdowski.pokerdemo.domain.PokerRepository
import com.chebdowski.pokerdemo.domain.Result
import com.chebdowski.pokerdemo.domain.Ring

class ReplayPokerRepository(private val replayPokerApi: ReplayPokerApi) : PokerRepository {

    override suspend fun getRings(): Result<List<Ring>> =
        replayPokerApi.rings().toResult { it.toDomain() }
}