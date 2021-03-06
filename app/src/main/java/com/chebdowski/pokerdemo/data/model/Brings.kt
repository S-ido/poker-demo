package com.chebdowski.pokerdemo.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Brings(
    @JsonProperty("min") val min: Long,
    @JsonProperty("max") val max: Long
)
