package com.chebdowski.pokerdemo.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Rings(
    @JsonProperty("id") val id: Int,
    @JsonProperty("name") val name: String,
    @JsonProperty("url") val url: String,
    @JsonProperty("server_version") val server_version: Int,
    @JsonProperty("type") val type: String,
    @JsonProperty("blinds") val blinds: Blinds,
    @JsonProperty("brings") val brings: Brings,
    @JsonProperty("closed") val closed: Boolean,
    @JsonProperty("fast") val fast: Boolean,
    @JsonProperty("seats") val seats: List<Seats?>,
    @JsonProperty("description") val description: String,
    @JsonProperty("promotions") val promotions: List<Promotion>,
    @JsonProperty("template_active") val template_active: Boolean,
    @JsonProperty("min_endorsement_rank") val min_endorsement_rank: Int,
    @JsonProperty("avgPot") val avgPot: Int,
    @JsonProperty("avgStack") val avgStack: Int,
    @JsonProperty("handHours") val handHours: Int,
    @JsonProperty("playersIds") val playersIds: List<Int>,
    @JsonProperty("waitingList") val waitingList: List<WaitingList>,
    @JsonProperty("stake") val stake: String,
    @JsonProperty("quickUrl") val quickUrl: String,
    @JsonProperty("template") val template: Int,
    @JsonProperty("game") val game: Game
)
