package com.chebdowski.pokerdemo.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Game(
    @JsonProperty("name") val name: String,
    @JsonProperty("variation") val variation: String,
    @JsonProperty("limit") val limit: String
)