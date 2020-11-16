package com.procurement.auction.infrastructure.web.response.version.jackson

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.procurement.auction.infrastructure.web.response.version.ApiVersion
import java.io.IOException

class ApiVersion2Deserializer : JsonDeserializer<ApiVersion>() {
    companion object {
        fun deserialize(text: String) = ApiVersion.orThrow(text) {
            IllegalAccessException("Invalid format of the api version. Expected: '${ApiVersion.pattern}', actual: '$text'.")
        }
    }

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(jsonParser: JsonParser, deserializationContext: DeserializationContext): ApiVersion =
        deserialize(
            jsonParser.text
        )
}
