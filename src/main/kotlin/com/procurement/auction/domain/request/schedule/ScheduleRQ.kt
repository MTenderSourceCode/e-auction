package com.procurement.auction.domain.request.schedule

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.procurement.auction.domain.ApiVersion
import com.procurement.auction.domain.binding.ApiVersionDeserializer
import com.procurement.auction.domain.binding.ApiVersionSerializer
import com.procurement.auction.domain.binding.JsonDateTimeDeserializer
import com.procurement.auction.domain.binding.JsonDateTimeSerializer
import com.procurement.auction.domain.request.Command
import java.time.LocalDateTime
import java.util.*

@JsonPropertyOrder("id", "command", "context", "data", "version")
data class ScheduleRQ(@field:JsonProperty("id") @param:JsonProperty("id") val id: String,
                      @field:JsonProperty("command") @param:JsonProperty("command") val command: Command,
                      @field:JsonProperty("context") @param:JsonProperty("context") val context: Context,
                      @field:JsonProperty("data") @param:JsonProperty("data") val data: Data,
                      @JsonDeserialize(using = ApiVersionDeserializer::class)
                      @JsonSerialize(using = ApiVersionSerializer::class)
                      @field:JsonProperty("version") @param:JsonProperty("version") val version: ApiVersion) {

    @JsonPropertyOrder("cpid", "operationId", "startDate", "pmd", "country")
    data class Context(@field:JsonProperty("cpid") @param:JsonProperty("cpid") val cpid: String,
                       @field:JsonProperty("operationId") @param:JsonProperty("operationId") val operationId: UUID,
                       @JsonDeserialize(using = JsonDateTimeDeserializer::class)
                       @JsonSerialize(using = JsonDateTimeSerializer::class)
                       @field:JsonProperty("startDate") @param:JsonProperty("startDate") val operationDate: LocalDateTime,
                       @field:JsonProperty("pmd") @param:JsonProperty("pmd") val pmd: String,
                       @field:JsonProperty("country") @param:JsonProperty("country") val country: String)

    @JsonPropertyOrder("tenderPeriod", "electronicAuctions")
    data class Data(@field:JsonProperty("tenderPeriod") @param:JsonProperty("tenderPeriod") val tenderPeriod: TenderPeriod,
                    @field:JsonProperty("electronicAuctions") @param:JsonProperty("electronicAuctions") val electronicAuctions: ElectronicAuctions
    )

    data class TenderPeriod(@JsonDeserialize(using = JsonDateTimeDeserializer::class)
                            @JsonSerialize(using = JsonDateTimeSerializer::class)
                            @field:JsonProperty("endDate") @param:JsonProperty("endDate") val endDate: LocalDateTime
    )

    data class ElectronicAuctions(@field:JsonProperty("details") @param:JsonProperty("details") val details: List<Detail>
    )

    @JsonPropertyOrder("id", "relatedLot", "electronicAuctionModalities")
    data class Detail(@field:JsonProperty("id") @param:JsonProperty("id") val id: String,
                      @field:JsonProperty("relatedLot") @param:JsonProperty("relatedLot") val relatedLot: String,
                      @field:JsonProperty("electronicAuctionModalities") @param:JsonProperty("electronicAuctionModalities") val electronicAuctionModalities: List<ElectronicAuctionModalities>
    )

    data class ElectronicAuctionModalities(@field:JsonProperty("eligibleMinimumDifference") @param:JsonProperty("eligibleMinimumDifference") val eligibleMinimumDifference: EligibleMinimumDifference)

    @JsonPropertyOrder("amount", "currency")
    data class EligibleMinimumDifference(@field:JsonProperty("amount") @param:JsonProperty("amount") val amount: Double,
                                         @field:JsonProperty("currency") @param:JsonProperty("currency") val currency: String
    )
}
