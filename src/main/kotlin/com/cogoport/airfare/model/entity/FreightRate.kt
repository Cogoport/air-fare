package com.cogoport.airfare.model.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.annotation.NonNull
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.time.ZonedDateTime
import java.util.*

@Introspected
@MappedEntity("air_freight_rates")
open class FreightRate(
    @field:Id @GeneratedValue @NonNull
    val id: UUID?,
    val originAirportId: UUID?,
    val destinationAirportId: UUID?,
    val commodity: String?,
    val commodityType: String?,
    val commoditySubType: String?,
    val airlineId: UUID?,
    val operationType: String?,
    val priceType: String?,
    val minPrice: Double?,
    val serviceProviderId: UUID?,
    val bulkOperationId: UUID?,
    val rateSheetId: UUID?,
    val performedById: UUID?,
    val procuredById: UUID?,
    val sourcedById: UUID?,
    var length: Int?,
    var breadth: Int?,
    var height: Int?,
    var maximumWeight: Int?,
    val shipmentType: String?,
    val stackingType: String?,
    var lastRateAvailableDate: ZonedDateTime?,
    var rateNotAvailableEntry: Boolean? = false,
    val cogoEntityId: UUID?
)
