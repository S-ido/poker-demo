package com.chebdowski.pokerdemo.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class User(
    @JsonProperty("id") val id: Int?,
    @JsonProperty("url") val url: String?,
    @JsonProperty("avatar") val avatar: String?,
    @JsonProperty("status") val status: String?,
    @JsonProperty("premium") val premium: Boolean?,
    @JsonProperty("name") val name: String?,
    @JsonProperty("purchases") val purchases: Boolean?,
    @JsonProperty("username") val username: String?,
    @JsonProperty("chips") val chips: Long?
)