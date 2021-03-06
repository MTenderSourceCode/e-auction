package com.procurement.auction.domain.model.breakdown.status

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.SerializerProvider
import com.procurement.auction.domain.model.ValueObjectSerializer
import java.io.IOException

class BreakdownStatusSerializer : ValueObjectSerializer<BreakdownStatus>() {
    companion object {
        fun serialize(breakdownStatus: BreakdownStatus) = breakdownStatus.value
    }

    @Throws(IOException::class, JsonProcessingException::class)
    override fun serialize(status: BreakdownStatus, jsonGenerator: JsonGenerator, provider: SerializerProvider) =
        jsonGenerator.writeString(serialize(
            status))
}