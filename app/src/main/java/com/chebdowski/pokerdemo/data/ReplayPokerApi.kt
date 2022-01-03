package com.chebdowski.pokerdemo.data

import com.chebdowski.pokerdemo.data.model.RingsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ReplayPokerApi {

    @GET("rings")
    suspend fun rings(): Response<RingsResponse>
}