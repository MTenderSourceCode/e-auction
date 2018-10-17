package com.procurement.auction.domain.schedule

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.procurement.auction.domain.ApiVersion
import com.procurement.auction.domain.binding.ApiVersionDeserializer
import com.procurement.auction.domain.binding.ApiVersionSerializer
import com.procurement.auction.domain.binding.JsonDateTimeDeserializer
import com.procurement.auction.domain.binding.JsonDateTimeSerializer
import java.time.LocalDateTime

@JsonPropertyOrder("version", "startDate", "usedSlots", "lots")
data class PlannedAuction(@JsonDeserialize(using = ApiVersionDeserializer::class)
                           @JsonSerialize(using = ApiVersionSerializer::class)
                           @field:JsonProperty("version") @param:JsonProperty("version") val version: ApiVersion,
                          @JsonSerialize(using = JsonDateTimeSerializer::class)
                           @JsonDeserialize(using = JsonDateTimeDeserializer::class)
                           @field:JsonProperty("startDate") @param:JsonProperty("startDate") val startDateTime: LocalDateTime,
                          @field:JsonProperty("usedSlots") @param:JsonProperty("usedSlots") val usedSlots: Set<Int>,
                          @field:JsonProperty("lots") @param:JsonProperty("lots") val lots: LinkedHashMap<String, Lot>) {

    @JsonPropertyOrder("id", "startDate", "url", "amount", "currency")
    data class Lot(@field:JsonProperty("id") @param:JsonProperty("id") val id: String,
                   @JsonSerialize(using = JsonDateTimeSerializer::class)
                   @JsonDeserialize(using = JsonDateTimeDeserializer::class)
                   @field:JsonProperty("startDate") @param:JsonProperty("startDate") val startDateTime: LocalDateTime,
                   @field:JsonProperty("url") @param:JsonProperty("url") val url: String,
                   @field:JsonProperty("amount") @param:JsonProperty("amount") val amount: Double,
                   @field:JsonProperty("currency") @param:JsonProperty("currency") val currency: String
    )
}