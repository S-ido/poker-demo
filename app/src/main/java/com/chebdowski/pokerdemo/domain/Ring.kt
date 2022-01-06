package com.chebdowski.pokerdemo.domain

data class Ring(
    val name: String,
    val gameType: String,
    val minBuyIn: Long,
    val maxBuyIn: Long
)
