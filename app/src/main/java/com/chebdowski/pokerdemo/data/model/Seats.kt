package com.chebdowski.pokerdemo.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Seats(
    @JsonProperty("id") val id: Int?,
    @JsonProperty("username") val username: String?,
    @JsonProperty("chips") val chips: Int?,
    @JsonProperty("url") val url: String?,
    @JsonProperty("avatar") val avatar: String?,
    @JsonProperty("country") val country: String?
)