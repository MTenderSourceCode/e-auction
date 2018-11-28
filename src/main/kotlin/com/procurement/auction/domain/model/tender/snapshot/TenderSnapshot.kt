package com.procurement.auction.domain.model.tender.snapshot

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.procurement.auction.domain.model.amount.Amount
import com.procurement.auction.domain.model.amount.AmountDeserializer
import com.procurement.auction.domain.model.amount.AmountSerializer
import com.procurement.auction.domain.model.auction.id.AuctionId
import com.procurement.auction.domain.model.auction.id.AuctionIdDeserializer
import com.procurement.auction.domain.model.auction.id.AuctionIdSerializer
import com.procurement.auction.domain.model.auction.status.AuctionsStatus
import com.procurement.auction.domain.model.auction.status.AuctionsStatusDeserializer
import com.procurement.auction.domain.model.auction.status.AuctionsStatusSerializer
import com.procurement.auction.domain.model.bid.id.BidId
import com.procurement.auction.domain.model.bid.id.BidIdDeserializer
import com.procurement.auction.domain.model.bid.id.BidIdSerializer
import com.procurement.auction.domain.model.breakdown.status.BreakdownStatus
import com.procurement.auction.domain.model.breakdown.status.BreakdownStatusDeserializer
import com.procurement.auction.domain.model.breakdown.status.BreakdownStatusSerializer
import com.procurement.auction.domain.model.country.Country
import com.procurement.auction.domain.model.country.CountryDeserializer
import com.procurement.auction.domain.model.country.CountrySerializer
import com.procurement.auction.domain.model.cpid.CPID
import com.procurement.auction.domain.model.cpid.CPIDDeserializer
import com.procurement.auction.domain.model.cpid.CPIDSerializer
import com.procurement.auction.domain.model.currency.Currency
import com.procurement.auction.domain.model.currency.CurrencyDeserializer
import com.procurement.auction.domain.model.currency.CurrencySerializer
import com.procurement.auction.domain.model.date.JsonDateTimeDeserializer
import com.procurement.auction.domain.model.date.JsonDateTimeSerializer
import com.procurement.auction.domain.model.lotId.LotId
import com.procurement.auction.domain.model.lotId.LotIdDeserializer
import com.procurement.auction.domain.model.lotId.LotIdSerializer
import com.procurement.auction.domain.model.operationId.OperationId
import com.procurement.auction.domain.model.platformId.PlatformId
import com.procurement.auction.domain.model.platformId.PlatformIdDeserializer
import com.procurement.auction.domain.model.platformId.PlatformIdSerializer
import com.procurement.auction.domain.model.progressId.ProgressId
import com.procurement.auction.domain.model.progressId.ProgressIdDeserializer
import com.procurement.auction.domain.model.progressId.ProgressIdSerializer
import com.procurement.auction.domain.model.sign.Sign
import com.procurement.auction.domain.model.sign.SignDeserializer
import com.procurement.auction.domain.model.sign.SignSerializer
import com.procurement.auction.domain.model.slots.id.SlotId
import com.procurement.auction.domain.model.slots.id.SlotsIdsDeserializer
import com.procurement.auction.domain.model.slots.id.SlotsIdsSerializer
import com.procurement.auction.domain.model.version.ApiVersion
import com.procurement.auction.domain.model.version.ApiVersionDeserializer
import com.procurement.auction.domain.model.version.ApiVersionSerializer
import com.procurement.auction.domain.model.version.RowVersion
import java.time.LocalDateTime

class TenderSnapshot(
    val rowVersion: RowVersion,
    val operationId: OperationId,
    val country: Country,
    val data: Data
) {
    val apiVersion: ApiVersion
        get() = data.apiVersion

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonPropertyOrder("version", "tender", "slots", "auctions")
    class Data(
        @JsonDeserialize(using = ApiVersionDeserializer::class)
        @JsonSerialize(using = ApiVersionSerializer::class)
        @field:JsonProperty("version") @param:JsonProperty("version") val apiVersion: ApiVersion,

        @field:JsonProperty("tender") @param:JsonProperty("tender") val tender: Tender,

        @JsonDeserialize(using = SlotsIdsDeserializer::class)
        @JsonSerialize(using = SlotsIdsSerializer::class)
        @field:JsonInclude(JsonInclude.Include.NON_EMPTY) @param:JsonInclude(JsonInclude.Include.NON_EMPTY)
        @field:JsonProperty("slots") @param:JsonProperty("slots") val slots: Set<SlotId>,

        @field:JsonProperty("auctions") @param:JsonProperty("auctions") val auctions: List<Auction>
    ) {

        @JsonPropertyOrder("id", "country", "status", "title", "description", "startDate", "endDate")
        class Tender(
            @JsonDeserialize(using = CPIDDeserializer::class)
            @JsonSerialize(using = CPIDSerializer::class)
            @field:JsonProperty("id") @param:JsonProperty("id") val id: CPID,

            @JsonDeserialize(using = CountryDeserializer::class)
            @JsonSerialize(using = CountrySerializer::class)
            @field:JsonProperty("country") @param:JsonProperty("country") val country: Country,

            @JsonDeserialize(using = AuctionsStatusDeserializer::class)
            @JsonSerialize(using = AuctionsStatusSerializer::class)
            @field:JsonProperty("status") @param:JsonProperty("status") val auctionsStatus: AuctionsStatus,

            @field:JsonInclude(JsonInclude.Include.NON_NULL) @param:JsonInclude(JsonInclude.Include.NON_NULL)
            @field:JsonProperty("title") @param:JsonProperty("title") val title: String? = null,

            @field:JsonInclude(JsonInclude.Include.NON_NULL) @param:JsonInclude(JsonInclude.Include.NON_NULL)
            @field:JsonProperty("description") @param:JsonProperty("description") val description: String? = null,

            @JsonDeserialize(using = JsonDateTimeDeserializer::class)
            @JsonSerialize(using = JsonDateTimeSerializer::class)
            @field:JsonInclude(JsonInclude.Include.NON_NULL) @param:JsonInclude(JsonInclude.Include.NON_NULL)
            @field:JsonProperty("startDate") @param:JsonProperty("startDate") val startDate: LocalDateTime? = null,

            @JsonDeserialize(using = JsonDateTimeDeserializer::class)
            @JsonSerialize(using = JsonDateTimeSerializer::class)
            @field:JsonInclude(JsonInclude.Include.NON_EMPTY) @param:JsonInclude(JsonInclude.Include.NON_EMPTY)
            @field:JsonProperty("endDate") @param:JsonProperty("endDate") val endDate: LocalDateTime? = null
        )

        @JsonPropertyOrder("id",
                           "lotId",
                           "title",
                           "description",
                           "auctionPeriod",
                           "value",
                           "modalities",
                           "bids",
                           "progress",
                           "results")
        class Auction(
            @JsonDeserialize(using = AuctionIdDeserializer::class)
            @JsonSerialize(using = AuctionIdSerializer::class)
            @field:JsonProperty("id") @param:JsonProperty("id") val id: AuctionId,

            @JsonDeserialize(using = LotIdDeserializer::class)
            @JsonSerialize(using = LotIdSerializer::class)
            @field:JsonProperty("lotId") @param:JsonProperty("lotId") val lotId: LotId,

            @field:JsonInclude(JsonInclude.Include.NON_NULL)
            @field:JsonProperty("title") @param:JsonProperty("title") val title: String? = null,

            @field:JsonInclude(JsonInclude.Include.NON_NULL)
            @field:JsonProperty("description") @param:JsonProperty("description") val description: String? = null,

            @field:JsonProperty("auctionPeriod") @param:JsonProperty("auctionPeriod") val auctionPeriod: AuctionPeriod,

            @field:JsonInclude(JsonInclude.Include.NON_NULL)
            @field:JsonProperty("value") @param:JsonProperty("value") val value: Value? = null,

            @field:JsonInclude(JsonInclude.Include.NON_EMPTY)
            @field:JsonProperty("modalities") @param:JsonProperty("modalities") val modalities: List<Modality> = emptyList(),

            @field:JsonInclude(JsonInclude.Include.NON_EMPTY)
            @field:JsonProperty("bids") @param:JsonProperty("bids") val bids: List<Bid>? = null,

            @field:JsonInclude(JsonInclude.Include.NON_EMPTY)
            @field:JsonProperty("progress") @param:JsonProperty("progress") val progress: List<Offer>? = null,

            @field:JsonInclude(JsonInclude.Include.NON_EMPTY)
            @field:JsonProperty("results") @param:JsonProperty("results") val results: List<Result>? = null
        ) {
            @JsonPropertyOrder("amount", "currency")
            class Value(
                @JsonDeserialize(using = AmountDeserializer::class)
                @JsonSerialize(using = AmountSerializer::class)
                @field:JsonProperty("amount") @param:JsonProperty("amount") val amount: Amount,

                @JsonDeserialize(using = CurrencyDeserializer::class)
                @JsonSerialize(using = CurrencySerializer::class)
                @field:JsonProperty("currency") @param:JsonProperty("currency") val currency: Currency
            )

            @JsonPropertyOrder("startDate", "endDate")
            class AuctionPeriod(
                @JsonDeserialize(using = JsonDateTimeDeserializer::class)
                @JsonSerialize(using = JsonDateTimeSerializer::class)
                @field:JsonProperty("startDate") @param:JsonProperty("startDate") val startDate: LocalDateTime,

                @field:JsonInclude(JsonInclude.Include.NON_NULL)
                @JsonDeserialize(using = JsonDateTimeDeserializer::class)
                @JsonSerialize(using = JsonDateTimeSerializer::class)
                @field:JsonProperty("endDate") @param:JsonProperty("endDate") val endDate: LocalDateTime? = null
            )

            @JsonPropertyOrder("url", "eligibleMinimumDifference")
            class Modality(
                @field:JsonProperty("url") @param:JsonProperty("url") val url: String,
                @field:JsonProperty("eligibleMinimumDifference") @param:JsonProperty("eligibleMinimumDifference") val eligibleMinimumDifference: EligibleMinimumDifference
            ) {

                @JsonPropertyOrder("amount", "currency")
                class EligibleMinimumDifference(
                    @JsonDeserialize(using = AmountDeserializer::class)
                    @JsonSerialize(using = AmountSerializer::class)
                    @field:JsonProperty("amount") @param:JsonProperty("amount") val amount: Amount,

                    @JsonDeserialize(using = CurrencyDeserializer::class)
                    @JsonSerialize(using = CurrencySerializer::class)
                    @field:JsonProperty("currency") @param:JsonProperty("currency") val currency: Currency
                )
            }

            @JsonPropertyOrder("id", "owner", "relatedLot", "value", "pendingDate", "url", "sign")
            class Bid(
                @JsonDeserialize(using = BidIdDeserializer::class)
                @JsonSerialize(using = BidIdSerializer::class)
                @field:JsonProperty("id") @param:JsonProperty("id") val id: BidId,

                @JsonDeserialize(using = PlatformIdDeserializer::class)
                @JsonSerialize(using = PlatformIdSerializer::class)
                @field:JsonProperty("owner") @param:JsonProperty("owner") val owner: PlatformId,

                @JsonDeserialize(using = LotIdDeserializer::class)
                @JsonSerialize(using = LotIdSerializer::class)
                @field:JsonProperty("relatedLot") @param:JsonProperty("relatedLot") val relatedLot: LotId,

                @field:JsonProperty("value") @param:JsonProperty("value") val value: Value,

                @JsonDeserialize(using = JsonDateTimeDeserializer::class)
                @JsonSerialize(using = JsonDateTimeSerializer::class)
                @field:JsonProperty("pendingDate") @param:JsonProperty("pendingDate") val pendingDate: LocalDateTime,

                @field:JsonProperty("url") @param:JsonProperty("url") val url: String,

                @JsonDeserialize(using = SignDeserializer::class)
                @JsonSerialize(using = SignSerializer::class)
                @field:JsonProperty("sign") @param:JsonProperty("sign") val sign: Sign
            ) {

                @JsonPropertyOrder("amount", "currency")
                class Value(
                    @JsonDeserialize(using = AmountDeserializer::class)
                    @JsonSerialize(using = AmountSerializer::class)
                    @field:JsonProperty("amount") @param:JsonProperty("amount") val amount: Amount,

                    @JsonDeserialize(using = CurrencyDeserializer::class)
                    @JsonSerialize(using = CurrencySerializer::class)
                    @field:JsonProperty("currency") @param:JsonProperty("currency") val currency: Currency
                )
            }

            @JsonPropertyOrder("id", "period", "breakdowns")
            class Offer(
                @JsonDeserialize(using = ProgressIdDeserializer::class)
                @JsonSerialize(using = ProgressIdSerializer::class)
                @field:JsonProperty("id") @param:JsonProperty("id") val id: ProgressId,

                @field:JsonProperty("period") @param:JsonProperty("period") val period: Period,

                @field:JsonProperty("breakdowns") @param:JsonProperty("breakdowns") val breakdowns: List<Breakdown>
            ) {

                @JsonPropertyOrder("startDate", "endDate")
                class Period(
                    @JsonDeserialize(using = JsonDateTimeDeserializer::class)
                    @JsonSerialize(using = JsonDateTimeSerializer::class)
                    @field:JsonProperty("startDate") @param:JsonProperty("startDate") val startDate: LocalDateTime,

                    @JsonDeserialize(using = JsonDateTimeDeserializer::class)
                    @JsonSerialize(using = JsonDateTimeSerializer::class)
                    @field:JsonProperty("endDate") @param:JsonProperty("endDate") val endDate: LocalDateTime
                )

                @JsonPropertyOrder("id", "status", "dateMet", "value")
                class Breakdown(
                    @JsonDeserialize(using = BidIdDeserializer::class)
                    @JsonSerialize(using = BidIdSerializer::class)
                    @field:JsonProperty("id") @param:JsonProperty("id") val relatedBid: BidId,

                    @JsonDeserialize(using = BreakdownStatusDeserializer::class)
                    @JsonSerialize(using = BreakdownStatusSerializer::class)
                    @field:JsonProperty("status") @param:JsonProperty("status") val status: BreakdownStatus,

                    @JsonDeserialize(using = JsonDateTimeDeserializer::class)
                    @JsonSerialize(using = JsonDateTimeSerializer::class)
                    @field:JsonProperty("dateMet") @param:JsonProperty("dateMet") val dateMet: LocalDateTime,

                    @field:JsonProperty("value") @param:JsonProperty("value") val value: Value
                ) {

                    @JsonPropertyOrder("amount", "currency")
                    class Value(
                        @JsonDeserialize(using = AmountDeserializer::class)
                        @JsonSerialize(using = AmountSerializer::class)
                        @field:JsonProperty("amount") @param:JsonProperty("amount") val amount: Amount,

                        @JsonDeserialize(using = CurrencyDeserializer::class)
                        @JsonSerialize(using = CurrencySerializer::class)
                        @field:JsonProperty("currency") @param:JsonProperty("currency") val currency: Currency
                    )
                }
            }

            @JsonPropertyOrder("relatedBid", "value")
            class Result(
                @JsonDeserialize(using = BidIdDeserializer::class)
                @JsonSerialize(using = BidIdSerializer::class)
                @field:JsonProperty("relatedBid") @param:JsonProperty("relatedBid") val relatedBid: BidId,

                @field:JsonProperty("value") @param:JsonProperty("value") val value: Value
            ) {

                @JsonPropertyOrder("amount", "currency")
                class Value(
                    @JsonDeserialize(using = AmountDeserializer::class)
                    @JsonSerialize(using = AmountSerializer::class)
                    @field:JsonProperty("amount") @param:JsonProperty("amount") val amount: Amount,

                    @JsonDeserialize(using = CurrencyDeserializer::class)
                    @JsonSerialize(using = CurrencySerializer::class)
                    @field:JsonProperty("currency") @param:JsonProperty("currency") val currency: Currency
                )
            }
        }
    }
}