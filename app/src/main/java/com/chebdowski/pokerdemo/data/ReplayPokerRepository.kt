package com.chebdowski.pokerdemo.data

import com.chebdowski.pokerdemo.domain.PokerRepository
import com.chebdowski.pokerdemo.domain.Ring
import com.chebdowski.pokerdemo.domain.SafeResult

class ReplayPokerRepository(val replayPokerApi: ReplayPokerApi) : PokerRepository {

    override suspend fun getRings(): SafeResult<List<Ring>> {
        TODO("not implemented")
    }
}