package com.chebdowski.pokerdemo.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Promotion(
    @JsonProperty("name") val name: String,
    @JsonProperty("url") val url: String
)
