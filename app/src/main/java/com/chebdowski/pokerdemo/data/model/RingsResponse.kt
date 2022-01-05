package com.chebdowski.pokerdemo.data.model

import com.chebdowski.pokerdemo.domain.Ring
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class RingsResponse(
    @JsonProperty("rings") val rings: List<Rings>
) {
    fun toDomain(): List<Ring> {
        return rings.map { ring -> Ring(ring.name, ring.game.name) }
    }
}