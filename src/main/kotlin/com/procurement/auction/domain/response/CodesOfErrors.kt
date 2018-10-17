package com.procurement.auction.domain.response

import com.procurement.auction.configuration.properties.GlobalProperties
import org.springframework.http.HttpStatus

enum class CodesOfErrors(val httpStatus: HttpStatus, group: String, id: String) {
    BAD_PAYLOAD(httpStatus = HttpStatus.BAD_REQUEST, group = "01", id = "01"),
    NO_LOT_FOR_AUCTIONS_PLANNING(httpStatus = HttpStatus.BAD_REQUEST, group = "01", id = "02"),
    OUT_OF_AUCTIONS(httpStatus = HttpStatus.BAD_REQUEST, group = "01", id = "03"),
    NO_DATA_IN_CALENDAR(httpStatus = HttpStatus.INTERNAL_SERVER_ERROR, group = "02", id = "01"),
    SERVER_ERROR(httpStatus = HttpStatus.INTERNAL_SERVER_ERROR, group = "00", id = "00");

    val code: String = "${httpStatus.value()}.${GlobalProperties.serviceId}.$group.$id"

    override fun toString(): String = code
}